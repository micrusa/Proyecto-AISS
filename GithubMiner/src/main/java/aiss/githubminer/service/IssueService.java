package aiss.githubminer.service;

import aiss.githubminer.model.github.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    @Value("${github.token}")
    public String token;

    public List<Issue> getAllIssues(String owner, String repo, int sinceDays, int maxPages) {
        List<Issue> allIssues = new ArrayList<>();
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(sinceDays);
        String sinceParam = sinceDate.format(DateTimeFormatter.ISO_DATE_TIME);

        int page = 1;
        while (page <= maxPages) {
            String uri = String.format("%s/%s/%s/issues?since=%s&page=%d",
                    baseuri, owner, repo, sinceParam, page);
            Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
            if (issues == null || issues.length == 0) {
                break;
            }
            allIssues.addAll(Arrays.asList(issues));
            page++;
        }

        return allIssues;

    }

    public Issue getIssue(String owner, String repo, String number) {
        String uri = baseuri + owner + "/" + repo + "/issues/" + number;
        Issue issue = restTemplate.getForObject(uri, Issue.class);
        return issue;
    }
}

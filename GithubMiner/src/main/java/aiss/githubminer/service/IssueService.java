package aiss.githubminer.service;

import aiss.githubminer.model.github.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    @Value("${github.token}")
    public String token;

    public List<Issue> getAllIssues(String owner, String repo, LocalDateTime sinceDays, int maxPages) {
        String uri = baseuri + owner + "/" + repo + "/issues?since=" + sinceDays + "&maxPages=" + maxPages;
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }

    public Issue getIssue(String owner, String repo, String number) {
        String uri = baseuri + owner + "/" + repo + "/issues/" + number;
        Issue issue = restTemplate.getForObject(uri, Issue.class);
        return issue;
    }
}

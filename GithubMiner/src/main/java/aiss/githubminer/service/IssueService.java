package aiss.githubminer.service;

import aiss.githubminer.model.github.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    @Value("${github.token}")
    public String token;

    public List<Issue> getAllIssues(String owner, String repo) {
        String uri = baseuri + owner + "/" + repo + "/issues";
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }

    public Issue getIssue(String owner, String repo, String number) {
        String uri = baseuri + owner + "/" + repo + "/issues/" + number;
        Issue issue = restTemplate.getForObject(uri, Issue.class);
        return issue;
    }

    public Issue getIssuesByState(String owner, String repo, String state) {
        String uri = baseuri + owner + "/" + repo + "/issues?state=" + state;
        Issue issue = restTemplate.getForObject(uri, Issue.class);
        return issue;
    }

    public List<Issue> getIssuesComments(String owner, String repo) {
        String uri = "https://api.github.com/repos/" + owner + "/" + repo + "/issues/comments";
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }
}

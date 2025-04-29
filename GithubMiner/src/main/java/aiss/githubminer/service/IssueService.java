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

    public List<Issue> getAllIssues(String owner, String repo) {
        String uri = baseuri + owner + "/" + repo + "/issues";
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }

    public Issue getIssue(String project, String id) {
        String uri = baseuri + project + "/issues/" + id;
        Issue issue = restTemplate.getForObject(uri, Issue.class);
        return issue;
    }

    public List<Issue> getIssuesByUser(String user) {
        String uri = "https://api.github.com/repos/" + user + "/issues";
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }

    public List<Issue> getIssuesByState(String project, String state) {
        String uri = "https://api.github.com/repos/" + project + "/issues?labels=" + state;
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }

    public List<Issue> getIssuesComments(String owner, String repo, String id) {
        String uri = "https://api.github.com/repos/" + owner + "/" + repo + "/issues/" + id + "/comments";
        Issue[] issues = restTemplate.getForObject(uri, Issue[].class);
        return List.of(issues);
    }
}

package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    BitbucketService bitbucketService;

    public List<Issue> getAllIssues(String owner, String repo) {
        String uri = owner + "/" + repo + "/issues";
        Issue[] issues = bitbucketService.getForAuthenticated(uri, Issue[].class);
        return List.of(issues);
    }

    public Issue getIssue(String project, String id) {
        String uri = project + "/issues/" + id;
        Issue issue = bitbucketService.getForAuthenticated(uri, Issue.class);
        return issue;
    }

    public List<Issue> getIssuesComments(String owner, String repo, String id) {
        String uri = owner + "/" + repo + "/issues/" + id + "/comments";
        Issue[] issues = bitbucketService.getForAuthenticated(uri, Issue[].class);
        return List.of(issues);
    }

}

package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.issue.Issue;
import aiss.bitbucketminer.model.bitBucket.issue.IssueContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    BitbucketService bitbucketService;

    public List<Issue> getAllIssues(String workspace, String repo_slug, int nIssues, int maxPages) {

        String uri = "repositories/" + workspace + "/" + repo_slug + "/issues?pagelen=" + nIssues;
        IssueContainer issueContainer = bitbucketService.getForAuthenticated(uri, IssueContainer.class);
        return issueContainer.getValues();



        /*String uri = "repositories/" + workspace + "/" + repo_slug + "/issues";
        Issue[] issues = bitbucketService.getForAuthenticated(uri, Issue[].class);
        return List.of(issues);*//*

        List<Issue> allIssues = new ArrayList<>();
        int page = 1;
        while (page <= maxPages) {
            String currentUri = "repositories/" + workspace + "/" + repo_slug + "/issues/" *//*?page=" + page*//*;

            List<Issue> issues = bitbucketService.getForAuthenticated(currentUri, List.class);

            if (issues == null || issues.isEmpty()) {
                break;
            }

            allIssues.addAll(issues);
            page++;
        }
        return allIssues;*/
    }

}

package aiss.githubminer.service;

import aiss.githubminer.model.github.issue.Issue;
import aiss.githubminer.utils.GithubUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    GithubService githubService;

    @Operation(
            summary = "Retrieve all issues",
            description = "Get all issue objects by owner and repo",
            tags = {"issues", "get"}
    )
    public List<Issue> getAllIssues(String owner, String repo, int sinceDays, int maxPages) {
        List<Issue> allIssues = new ArrayList<>();
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(sinceDays);
        String sinceParam = sinceDate.format(DateTimeFormatter.ISO_DATE_TIME);

        int page = 1;
        String currentUri = String.format("%s/%s/%s/issues?since=%s&page=%d",
                githubService.baseuri, owner, repo, sinceParam, page);

        while (page++ <= maxPages && currentUri != null) {
            ResponseEntity<Issue[]> response = githubService.getAuthenticatedFullUri(currentUri, Issue[].class);
            Issue[] issues = response.getBody();

            if (issues == null || issues.length == 0) {
                break;
            }

            allIssues.addAll(Arrays.asList(issues));
            currentUri = GithubUtils.getNextPageUrl(response.getHeaders()) == null ? null : String.format("%s/%s/%s/issues?since=%s&page=%d",
                    githubService.baseuri, owner, repo, sinceParam, page);
        }

        return allIssues;

    }

    @Operation(
            summary = "Retrieve issue by ID",
            description = "Get issue object by specifying its ID",
            tags = {"issues", "get"}
    )
    public Issue getIssue(String owner, String repo, String number) {
        String uri = owner + "/" + repo + "/issues/" + number;
        return githubService.getAuthenticated(uri, Issue.class).getBody();
    }

}

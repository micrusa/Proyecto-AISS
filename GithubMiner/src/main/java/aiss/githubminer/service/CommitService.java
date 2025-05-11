package aiss.githubminer.service;

import aiss.githubminer.model.github.commit.Commit;
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
public class CommitService {

    @Autowired
    GithubService githubService;

    @Operation(
            summary = "Retrieve all commits",
            description = "Get all commit objects by owner and repo",
            tags = {"commits", "get"}
    )
    public List<Commit> getCommits(String owner, String repo_slug, int sinceCommits, int maxPages) {
        List<Commit> allCommits = new ArrayList<>();
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(sinceCommits);
        String sinceParam = sinceDate.format(DateTimeFormatter.ISO_DATE_TIME);

        int page = 1;
        String currentUri = String.format("%s/%s/%s/commits?since=%s&page=%d", githubService.baseuri, owner, repo_slug, sinceParam, 1);

        while (page++ <= maxPages && currentUri != null) {
            ResponseEntity<Commit[]> response = githubService.getAuthenticatedFullUri(currentUri, Commit[].class);
            Commit[] commits = response.getBody();
            if (commits == null || commits.length == 0) {
                break;
            }
            allCommits.addAll(Arrays.asList(commits));
            currentUri = GithubUtils.getNextPageUrl(response.getHeaders());
        }

        return allCommits;
    }

}

package aiss.githubminer.service;

import aiss.githubminer.model.github.commit.Commit;
import aiss.githubminer.utils.GithubUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    GithubService githubService;

    public List<Commit> getCommits(String owner, String repo, int sinceCommits, int maxPages) {
        List<Commit> allCommits = new ArrayList<>();
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(sinceCommits);
        String sinceParam = sinceDate.format(DateTimeFormatter.ISO_DATE_TIME);

        int page = 1;
        String currentUri = String.format("%s/%s/commits?since=%s&page=%d", owner, repo, sinceParam, 1);

        while (page++ <= maxPages && currentUri != null) {
            ResponseEntity<Commit[]> response = githubService.getAuthenticated(currentUri, Commit[].class);
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

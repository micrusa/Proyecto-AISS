package aiss.githubminer.service;

import aiss.githubminer.model.github.commit.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;
/*
    public List<Commit> getCommits(String owner, String repo) {
        String uri = baseuri + owner + "/" + repo + "/commits";
        Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
        return List.of(commits);
    }
*/

    public List<Commit> getCommits(String owner, String repo, int sinceCommits, int maxPages) {
        List<Commit> allCommits = new ArrayList<>();
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(sinceCommits);
        String sinceParam = sinceDate.format(DateTimeFormatter.ISO_DATE_TIME);

        int page = 1;
        while (page <= maxPages) {
            String uri = String.format("%s/%s/%s/commits?since=%s&page=%d",
                    baseuri, owner, repo, sinceParam, page);
            Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
            if (commits == null || commits.length == 0) {
                break;
            }
            allCommits.addAll(Arrays.asList(commits));
            page++;
        }

        return allCommits;
    }

}

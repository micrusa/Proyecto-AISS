package aiss.githubminer.service;

import aiss.githubminer.model.github.commit.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    public List<Commit> getCommits(String owner, String repo, int sinceDays, int maxPages ) {
        String uri = baseuri + owner + "/" + repo + "/commits?since=" + sinceDays + "&maxPages=" + maxPages;
        Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
        return List.of(commits);
    }


}

package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.project.Project;
import aiss.bitbucketminer.model.gitMiner.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    String baseUri = "https://api.bitbucket.org/2.0/";

    String token = "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";

    public Commit getCommit(String owner, String repo, String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + owner + "/" + repo + "/commits/" + id;
        Commit commit = restTemplate.getForObject(uri, Commit.class);
        return commit;
    }

    public List<Commit> getCommits(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + owner + "/" + repo + "/commits/";
        Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
        return List.of(commits);

    }
}
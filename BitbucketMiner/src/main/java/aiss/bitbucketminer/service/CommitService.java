package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.project.Project;
import aiss.bitbucketminer.model.gitMiner.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${bitbucket.baseUri}")
    public String baseUri;
    @Value("${bitbucket.baseUri}")
    public String token;

    public Commit getCommit(String workspace, String repo_slug, String commit) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + "/repositories/" + workspace + "/" + repo_slug + "/commits/" + commit;
        Commit com = restTemplate.getForObject(uri, Commit.class);
        return com;
    }

    public List<Commit> getCommits(String workspace, String repo_slug) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + "/repositories/" + workspace + "/" + repo_slug + "/commits/";
        Commit[] commits = restTemplate.getForObject(uri, Commit[].class);
        return List.of(commits);

    }
}
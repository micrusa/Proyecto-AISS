package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.project.Project;
import aiss.bitbucketminer.model.bitBucket.commit.Commit;
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
    BitbucketService bitbucketService;

    public Commit getCommit(String workspace, String repo_slug, String commit) {
        String uri = "repositories/" + workspace + "/" + repo_slug + "/commits/" + commit;
        return bitbucketService.getForAuthenticated(uri, Commit.class);
    }

    public List<Commit> getCommits(String workspace, String repo_slug) {
        String uri = "repositories/" + workspace + "/" + repo_slug + "/commits";
        Commit[] commits = bitbucketService.getForAuthenticated(uri, Commit[].class);
        return List.of(commits);

    }
}
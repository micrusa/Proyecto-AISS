package aiss.bitbucketminer.service;

import aiss.bitbucketminer.exception.CommitNotFoundException;
import aiss.bitbucketminer.model.bitBucket.commit.Commit;
import aiss.bitbucketminer.model.bitBucket.commit.CommitContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitService {

    @Autowired
    BitbucketService bitbucketService;

    public Commit getCommit(String workspace, String repo_slug, String commit) throws CommitNotFoundException {
        String uri = "repositories/" + workspace + "/" + repo_slug + "/commits/" + commit;
        Commit result = bitbucketService.getForAuthenticated(uri, Commit.class);
        if (result == null) {
            throw new CommitNotFoundException();
        }
        return result;
    }

    public List<Commit> getCommits(String workspace, String repo_slug) {
        String uri = "repositories/" + workspace + "/" + repo_slug + "/commits";
        CommitContainer commits = bitbucketService.getForAuthenticated(uri, CommitContainer.class);
        return commits.getValues();

    }
}



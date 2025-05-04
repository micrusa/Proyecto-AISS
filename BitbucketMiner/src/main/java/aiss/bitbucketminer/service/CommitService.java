package aiss.bitbucketminer.service;

import aiss.bitbucketminer.exception.CommitNotFoundException;
import aiss.bitbucketminer.model.bitBucket.commit.Commit;
import aiss.bitbucketminer.model.bitBucket.commit.CommitContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Commit> getCommits(String workspace, String repo_slug, int nCommits, int maxPages) {
        /*String uri = "repositories/" + workspace + "/" + repo_slug + "/commits";
        CommitContainer commits = bitbucketService.getForAuthenticated(uri, CommitContainer.class);
        return commits.getValues();*/

        List<Commit> allCommits = new ArrayList<>();
        int page = 1;

        while (page <= maxPages) {
            // Construir la URI manualmente
            String currentUri = "repositories/" + workspace + "/" + repo_slug + "/commits?page=" + page;

            // Usar BitbucketService para realizar la llamada autenticada
            CommitContainer commitContainer = bitbucketService.getForAuthenticated(currentUri, CommitContainer.class);

            if (commitContainer == null || commitContainer.getValues().isEmpty()) {
                break;
            }

            allCommits.addAll(commitContainer.getValues());
            page++;
        }

        return allCommits;
    }
}



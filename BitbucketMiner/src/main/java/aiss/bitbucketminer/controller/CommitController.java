package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.bitBucket.commit.Commit;
import aiss.bitbucketminer.service.CommitService;
import aiss.bitbucketminer.exception.CommitNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/commits")
public class CommitController {

    @Autowired
    private CommitService commitService;

    @GetMapping("/{workspace}/{repo_slug}/{commit}")
    public Commit getCommit(
            @PathVariable String workspace,
            @PathVariable String repo_slug,
            @PathVariable String commit) throws CommitNotFoundException {
        return commitService.getCommit(workspace, repo_slug, commit);
    }
}



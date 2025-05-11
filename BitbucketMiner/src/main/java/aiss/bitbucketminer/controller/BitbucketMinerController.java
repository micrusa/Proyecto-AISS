package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.etl.Transformer;
import aiss.bitbucketminer.model.bitBucket.comment.Comment;
import aiss.bitbucketminer.model.bitBucket.commit.Commit;
import aiss.bitbucketminer.model.bitBucket.issue.Issue;
import aiss.bitbucketminer.model.bitBucket.repositories.Repository;
import aiss.bitbucketminer.model.gitMiner.Project;
import aiss.bitbucketminer.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "BitbucketMiner", description = "BitbucketMiner API")
@RestController
@RequestMapping("/bitbucket")
public class BitbucketMinerController {

    @Autowired
    private CommitService commitService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private Transformer transformer;

    @Autowired
    private GitMinerService gitMinerService;

    @Operation(
            summary = "Process Bitbucket data",
            description = "Process Bitbucket data for a specific repository and store it in the database",
            tags = {"bitbucket", "post"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema())
            }),
    })
    @PostMapping("/{workspace}/{repoSlug}")
    public ResponseEntity<Object> processBitbucketData(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(defaultValue = "2") int sinceCommits,
            @RequestParam(defaultValue = "20") int sinceIssues,
            @RequestParam(defaultValue = "2") int maxPages
    ) {
        Project gitMinerProject = readProject(workspace, repoSlug, sinceCommits, sinceIssues, maxPages);
        gitMinerService.createProject(gitMinerProject);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @Operation(
            summary = "Retrieve Bitbucket data",
            description = "Get Bitbucket data for a specific repository",
            tags = {"bitbucket", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Project.class))
            }),
    })
    @GetMapping("/{workspace}/{repoSlug}")
    public ResponseEntity<Project> getBitbucketData(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(name = "nCommits", defaultValue = "2") int nCommits,
            @RequestParam(name = "nIssues", defaultValue = "20") int nIssues,
            @RequestParam(defaultValue = "2") int maxPages
    )
    {
        Project gitMinerProject = readProject(workspace, repoSlug, nCommits, nIssues, maxPages);
        return ResponseEntity.ok(gitMinerProject);
    }

    private Project readProject(String workspace, String repoSlug, int nCommits, int nIssues, int maxPages) {
        List<Commit> commits = commitService.getCommits(workspace, repoSlug, nCommits, maxPages);
        List<Issue> issues = issueService.getAllIssues(workspace, repoSlug, nIssues, maxPages);

        Repository bitbucketProject = repositoryService.getRepository(workspace, repoSlug);

        aiss.bitbucketminer.model.gitMiner.Project gitMinerProject = transformer.transformProject(bitbucketProject);

        List<aiss.bitbucketminer.model.gitMiner.Commit> gitMinerCommits = commits.stream()
                .map(transformer::transformCommit)
                .collect(Collectors.toList());

        List<aiss.bitbucketminer.model.gitMiner.Issue> gitMinerIssues = new ArrayList<>();
        for (Issue issue : issues) {
            aiss.bitbucketminer.model.gitMiner.Issue gitMinerIssue = transformer.transformIssue(issue);

            // TODO: Corregir estos comments. Se están leyendo los comments del commit, y tienen q ser los commits del issue
            List<Comment> comments = new ArrayList<>();//commentService.getComments(workspace, repoSlug, issue.get, 1);

            List<aiss.bitbucketminer.model.gitMiner.Comment> gitMinerComments = comments.stream()
                    .map(transformer::transformComment).toList();
            gitMinerIssue.setComments(gitMinerComments);
            gitMinerIssues.add(gitMinerIssue);
        }

        gitMinerProject.setCommits(gitMinerCommits);
        gitMinerProject.setIssues(gitMinerIssues);

        return gitMinerProject;
    }

}
package aiss.githubminer.controller;

import aiss.githubminer.etl.Transformer;
import aiss.githubminer.model.github.project.Project;
import aiss.githubminer.model.github.comment.Comment;
import aiss.githubminer.model.github.commit.Commit;
import aiss.githubminer.model.github.issue.Issue;
import aiss.githubminer.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.Percentage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "GithubMiner", description = "GithubMiner API")
@RestController
@RequestMapping("/github")
public class GithubMinerController {

    @Autowired
    private CommitService commitService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private Transformer transformer;

    @Autowired
    private GitMinerService gitMinerService;


    @Operation(
            summary = "Process Github data",
            description = "Process Github data for a specific repository and store it in the database",
            tags = {"github", "post"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema())
            }),
    })
    @PostMapping("/{owner}/{repoName}")
    public ResponseEntity<Object> processGithubData(
            @Parameter(description = "Owner of the repository to process", required = true)
            @PathVariable("owner") String owner,
            @Parameter(description = "Name of the repository to process", required = true)
            @PathVariable("repoName") String repoName,
            @Parameter(description = "Number of commits to retrieve")
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @Parameter(description = "Number of issues to retrieve")
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @Parameter(description = "Maximum number of pages to retrieve")
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages
    ) {
        aiss.githubminer.model.gitminer.Project gitMinerProject = readProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
        gitMinerService.createProject(gitMinerProject);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @Operation(
            summary = "Get Github data",
            description = "Retrieve Github data for a specific repository",
            tags = {"github", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Project.class))
            }),
    })
    @GetMapping("/{owner}/{repoName}")
    public ResponseEntity<aiss.githubminer.model.gitminer.Project> getGithubData(
            @Parameter(description = "Owner of the repository to process", required = true)
            @PathVariable String owner,
            @Parameter(description = "Name of the repository to process", required = true)
            @PathVariable String repoName,
            @Parameter(description = "Number of commits to retrieve")
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @Parameter(description = "Number of issues to retrieve")
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @Parameter(description = "Maximum number of pages to retrieve")
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages
    ) {
        aiss.githubminer.model.gitminer.Project gitMinerProject = readProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
        return ResponseEntity.ok(gitMinerProject);
    }

    private aiss.githubminer.model.gitminer.Project readProject(String owner, String repoName, int sinceCommits, int sinceIssues, int maxPages) {
        List<Commit> commits = commitService.getCommits(owner, repoName, sinceCommits, maxPages);
        List<Issue> issues = issueService.getAllIssues(owner, repoName, sinceIssues, maxPages);
        Project githubProject = projectService.getProject(owner, repoName);

        //  Transformar los datos a modelos de GitMiner
        aiss.githubminer.model.gitminer.Project gitMinerProject = transformer.transformProject(githubProject);
        List<aiss.githubminer.model.gitminer.Commit> gitMinerCommits = commits.stream()
                .map(transformer::transformCommit)
                .filter(Objects::nonNull) // Necesario para cumplir restricciones
                .collect(java.util.stream.Collectors.toList());
        List<aiss.githubminer.model.gitminer.Issue> gitMinerIssues = new ArrayList<>();

        for (Issue issue : issues) {
            List<Comment> comments = commentService.getComments(owner, repoName, issue.getNumber());

            aiss.githubminer.model.gitminer.Issue gitMinerIssue = transformer.transformIssue(issue);
            gitMinerIssue.setComments(comments.stream().map(transformer::transformComment).toList());

            gitMinerIssues.add(gitMinerIssue);
        }

        gitMinerProject.setCommits(gitMinerCommits);
        gitMinerProject.setIssues(gitMinerIssues);
        return gitMinerProject;
    }
}

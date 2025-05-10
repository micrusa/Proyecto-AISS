package aiss.githubminer.controller;

import aiss.githubminer.etl.Transformer;
import aiss.githubminer.model.github.project.Project;
import aiss.githubminer.model.github.comment.Comment;
import aiss.githubminer.model.github.commit.Commit;
import aiss.githubminer.model.github.issue.Issue;
import aiss.githubminer.service.*;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jfr.Percentage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @PostMapping("/{owner}/{repoName}")
    public ResponseEntity<Object> processGithubData(
            @PathVariable("owner") String owner,
            @PathVariable("repoName") String repoName,
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
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
    @GetMapping("/{owner}/{repoName}")
    public ResponseEntity<aiss.githubminer.model.gitminer.Project> getGithubData(
            @PathVariable String owner,
            @PathVariable String repoName,
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
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

package aiss.githubminer.controller;

import aiss.githubminer.etl.Transformer;
import aiss.githubminer.model.github.project.Project;
import aiss.githubminer.model.github.comment.Comment;
import aiss.githubminer.model.github.commit.Commit;
import aiss.githubminer.model.github.issue.Issue;
import aiss.githubminer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/{owner}/{repoName}")
    public void processGithubData(
            @PathVariable("owner") String owner,
            @PathVariable("repoName") String repoName,
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages
    ) {
        //  Obtener datos de GitHub usando los servicios
        List<Commit> commits = commitService.getCommits(owner, repoName, sinceCommits, maxPages);
        List<Issue> issues = issueService.getAllIssues(owner, repoName, sinceIssues, maxPages);
        List<Comment> comments = commentService.getComments(owner, repoName);
        Project githubProject = projectService.getProject(owner, repoName);

        //  Transformar los datos a modelos de GitMiner
        aiss.githubminer.model.gitminer.Project gitMinerProject = transformer.transformProject(githubProject);
        List<aiss.githubminer.model.gitminer.Commit> gitMinerCommits = commits.stream()
                .map(transformer::transformCommit)
                .collect(java.util.stream.Collectors.toList());
        List<aiss.githubminer.model.gitminer.Issue> gitMinerIssues = issues.stream()
                .map(transformer::transformIssue)
                .collect(java.util.stream.Collectors.toList());

        gitMinerProject.setCommits(gitMinerCommits);
        gitMinerProject.setIssues(gitMinerIssues);

        List<aiss.githubminer.model.gitminer.Comment> gitMinerComments = comments.stream()
                .map(transformer::transformComment)
                .toList(); // TODO: Arreglar los comments, no hay que coger los comments del repositorio sino de cada issue y asociarlo

        gitMinerService.createProject(gitMinerProject);
    }

    @GetMapping("/{owner}/{repoName}")
    public ResponseEntity<Object> getGithubData(
            @PathVariable String owner,
            @PathVariable String repoName,
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages
    ) {
        try {
            List<Commit> commits = commitService.getCommits(owner, repoName, sinceCommits, maxPages);
            List<Issue> issues = issueService.getAllIssues(owner, repoName, sinceIssues, maxPages);
            List<Comment> comments = commentService.getComments(owner, repoName);
            Project project = projectService.getProject(owner, repoName);


            Map<String, Object> response = new HashMap<>();
            response.put("project", project);
            response.put("commits", commits);
            response.put("issues", issues);
            response.put("comments", comments);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" Error al obtener datos de GitHub: " + e.getMessage());
        }
    }
}

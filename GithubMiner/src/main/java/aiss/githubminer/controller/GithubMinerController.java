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
import java.util.stream.Collectors;

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
        List<Comment> comments = issues.stream()
                .flatMap(issue -> commentService.getComments(owner, repoName, issue.getNumber()).stream())
                .toList();
        Project githubProject = projectService.getProject(owner, repoName);

        //  Transformar los datos a modelos de GitMiner
        aiss.githubminer.model.gitminer.Project gitMinerProject = transformer.transformProject(githubProject);
        List<aiss.githubminer.model.gitminer.Commit> gitMinerCommits = commits.stream()
                .map(transformer::transformCommit)
                .collect(java.util.stream.Collectors.toList());
        List<aiss.githubminer.model.gitminer.Issue> gitMinerIssues = issues.stream()
                .map(transformer::transformIssue)
                .collect(java.util.stream.Collectors.toList());

        List<aiss.githubminer.model.gitminer.Comment> gitMinerComments = comments.stream()
                .map(transformer::transformComment)
                .collect(Collectors.toList());

        for (aiss.githubminer.model.gitminer.Issue gitMinerIssue : gitMinerIssues) {
            List<aiss.githubminer.model.gitminer.Comment> issueComments = gitMinerComments.stream()
                    .filter(comment -> {
                        String issueUrl = comment.getIssueUrl();
                        if (issueUrl != null) {
                            String issueNumberFromCommentUrl = issueUrl.substring(issueUrl.lastIndexOf("/") + 1);
                            return issueNumberFromCommentUrl.equals(gitMinerIssue.getRefId());
                            //veo si el id de la issueurl coincide con el refid de la issue pero por algun motivo la issueurl de los comment sale como null y no debería de ser así
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
            gitMinerIssue.setComments(issueComments);
        }

        gitMinerProject.setCommits(gitMinerCommits);
        gitMinerProject.setIssues(gitMinerIssues);


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
            List<Comment> comments = issues.stream()
                    .flatMap(issue -> commentService.getComments(owner, repoName, issue.getNumber()).stream())
                    .toList();
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

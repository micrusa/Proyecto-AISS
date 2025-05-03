package aiss.githubminer.controller;

import aiss.githubminer.etl.Transformer;
import aiss.githubminer.model.github.project.Project;
import aiss.githubminer.model.github.comment.Comment;
import aiss.githubminer.model.github.commit.Commit;
import aiss.githubminer.model.github.issue.Issue;
import aiss.githubminer.service.CommentService;
import aiss.githubminer.service.CommitService;
import aiss.githubminer.service.IssueService;
import aiss.githubminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/githubminer")
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
    private RestTemplate restTemplate;

    @Value("${gitminer.baseuri}")
    private String gitMinerUri;

    @PostMapping("/{owner}/{repoName}")
    public void processGithubData(
            @PathVariable("owner") String owner,
            @PathVariable("repoName") String repoName,
            @RequestParam(value = "sinceCommits", defaultValue = "2") int sinceCommits,
            @RequestParam(value = "sinceIssues", defaultValue = "20") int sinceIssues,
            @RequestParam(value = "maxPages", defaultValue = "2") int maxPages
    ) {
        // 1. Obtener datos de GitHub usando los servicios
        List<Commit> commits = commitService.getCommits(owner, repoName, sinceCommits, maxPages);
        List<Issue> issues = issueService.getAllIssues(owner, repoName, sinceIssues, maxPages);
        List<Comment> comments = commentService.getComments(owner, repoName);
        Project githubProject = projectService.getProject(owner, repoName);

        // 2. Transformar los datos a modelos de GitMiner
        aiss.githubminer.model.gitminer.Project gitMinerProject = transformer.githubTransformProject(githubProject);
        List<aiss.githubminer.model.gitminer.Commit> gitMinerCommits = commits.stream()
                .map(transformer::githubTransformCommit)
                .collect(java.util.stream.Collectors.toList());
        List<aiss.githubminer.model.gitminer.Issue> gitMinerIssues = issues.stream()
                .map(transformer::githubTransformIssue)
                .collect(java.util.stream.Collectors.toList());
        List<aiss.githubminer.model.gitminer.Comment> gitMinerComments = comments.stream()
                .map(transformer::githubTransformComment)
                .collect(java.util.stream.Collectors.toList());

        // 3. Enviar los datos a GitMiner (usando RestTemplate para interactuar con la API de GitMiner)
        sendToGitMiner(gitMinerProject, "/projects");
        gitMinerCommits.forEach(commit -> sendToGitMiner(commit, "/commits"));
        gitMinerIssues.forEach(issue -> sendToGitMiner(issue, "/issues"));
        gitMinerComments.forEach(comment -> sendToGitMiner(comment, "/comments"));
    }
    private <T> void sendToGitMiner(T data, String endpoint) {
        // Se prepara el encabezado para enviar los datos en formato JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<T> request = new HttpEntity<>(data, headers);

        // Se hace la petición POST a la API de GitMiner
        ResponseEntity<String> response = restTemplate.exchange(
                gitMinerUri + endpoint,
                HttpMethod.POST,
                request,
                String.class
        );

        // Verifica la respuesta
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error enviando datos a GitMiner: " + response.getStatusCode());
        }
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

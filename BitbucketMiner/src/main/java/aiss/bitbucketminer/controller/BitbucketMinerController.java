package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.etl.Transformer;
import aiss.bitbucketminer.model.bitBucket.comment.Comment;
import aiss.bitbucketminer.model.bitBucket.commit.Commit;
import aiss.bitbucketminer.model.bitBucket.issue.Issue;
import aiss.bitbucketminer.model.bitBucket.project.Project;
import aiss.bitbucketminer.service.CommentService;
import aiss.bitbucketminer.service.CommitService;
import aiss.bitbucketminer.service.IssueService;
import aiss.bitbucketminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private ProjectService projectService;

    @Autowired
    private Transformer transformer;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gitminer.baseuri}")
    private String gitMinerUri;

    @PostMapping("/{workspace}/{repoSlug}/{projectKey}")
    public void processBitbucketData(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @PathVariable String projectKey,
            @RequestParam(defaultValue = "2") int sinceCommits,
            @RequestParam(defaultValue = "20") int sinceIssues,
            @RequestParam(defaultValue = "2") int maxPages
    ) {
        List<Commit> commits = commitService.getCommits(workspace, repoSlug, sinceCommits, maxPages);
        List<Issue> issues = issueService.getAllIssues(workspace, repoSlug, sinceIssues, maxPages);

        List<Comment> comments = commits.stream()
                .flatMap(c -> commentService.getComments(workspace, repoSlug, c.getHash(), 1).stream())
                .collect(Collectors.toList());

        Project bitbucketProject = projectService.getProject(workspace, projectKey);

        aiss.bitbucketminer.model.gitMiner.Project gitMinerProject = transformer.bitbucketTransformProject(bitbucketProject);

        List<aiss.bitbucketminer.model.gitMiner.Commit> gitMinerCommits = commits.stream()
                .map(transformer::bitbucketTransformCommit)
                .collect(Collectors.toList());

        List<aiss.bitbucketminer.model.gitMiner.Issue> gitMinerIssues = issues.stream()
                .map(transformer::bitbucketTransformIssue)
                .collect(Collectors.toList());
        List<aiss.bitbucketminer.model.gitMiner.Comment> gitMinerComments = comments.stream()
                .map(transformer::bitbucketTransformComment)
                .collect(Collectors.toList());

        // Enviar datos transformados a GitMiner
        sendToGitMiner(gitMinerProject, "/projects");
        gitMinerCommits.forEach(commit -> sendToGitMiner(commit, "/commits"));
        gitMinerIssues.forEach(issue -> sendToGitMiner(issue, "/issues"));
        gitMinerComments.forEach(comment -> sendToGitMiner(comment, "/comments"));
    }

    private <T> void sendToGitMiner(T data, String endpoint) {
        HttpHeaders headers = new HttpHeaders(); // Cabecera HTTP
        headers.setContentType(MediaType.APPLICATION_JSON); // Especificamos JSON como tipo de contenido

        HttpEntity<T> request = new HttpEntity<>(data, headers); // Cuerpo de la petición

        ResponseEntity<String> response = restTemplate.exchange(
                gitMinerUri + endpoint,
                HttpMethod.POST,
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error enviando datos a GitMiner: " + response.getStatusCode());
        }
    }

    @GetMapping("/{workspace}/{repoSlug}")
    public ResponseEntity<Object> getBitbucketData(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(name = "projectKey") String projectKey,
            @RequestParam(name = "nCommits", defaultValue = "2") int nCommits,
            @RequestParam(name = "nIssues", defaultValue = "20") int nIssues,
            @RequestParam(defaultValue = "2") int maxPages
    )
    {
        try {
            List<Commit> commits = commitService.getCommits(workspace, repoSlug, nCommits, maxPages);
            List<Issue> issues = issueService.getAllIssues(workspace, repoSlug, nIssues, maxPages);
            List<Comment> comments = commits.stream()
                    .flatMap(c -> commentService.getComments(workspace, repoSlug, c.getHash(), 1).stream())
                    .collect(Collectors.toList());
            Project project = projectService.getProject(workspace, projectKey);

            Map<String, Object> response = new HashMap<>();
            response.put("project", project);
            response.put("commits", commits);
            response.put("issues", issues);
            response.put("comments", comments);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener datos de Bitbucket: " + e.getMessage());
        }
    }

}

package aiss.githubminer.service;

import aiss.githubminer.model.github.project.Project;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    GithubService githubService;

    @Operation(
            summary = "Retrieve all projects",
            description = "Get all project objects by owner and repo",
            tags = {"projects", "get"}
    )
    public Project getProject(String owner, String repo) {
        String uri = owner + "/" + repo ;
        ResponseEntity<Project> response = githubService.getAuthenticated(uri, Project.class);
        return response.getBody();
    }

}

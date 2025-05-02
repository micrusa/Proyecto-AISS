package aiss.githubminer.service;

import aiss.githubminer.model.github.project.Project;
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
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    @Value("${github.token}")
    public String token;

    public Project getProject(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String uri = baseuri + id;
        Project projectDetails = restTemplate.getForObject(uri, Project.class);
        return projectDetails;
    }

    public List<Project> getProjects(String owner, String repo) {
        String uri = baseuri + owner + "/" + repo + "/projects";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Project[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Project[].class);
        Project[] projects = response.getBody();
        if (projects != null) {
            return Arrays.asList(projects);
        } else {
            return null;
        }
    }


    public Project createProject(String owner, String repo, Project project) {
        String uri = baseuri + owner + "/" + repo + "/projects";
        Project createdProject = restTemplate.postForObject(uri, project, Project.class);
        return createdProject;
    }


}

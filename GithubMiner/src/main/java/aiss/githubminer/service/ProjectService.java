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

    public Project getProject(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String uri = baseuri + owner + "/" + repo ;
        Project projectDetails = restTemplate.getForObject(uri, Project.class);
        return projectDetails;
    }

}

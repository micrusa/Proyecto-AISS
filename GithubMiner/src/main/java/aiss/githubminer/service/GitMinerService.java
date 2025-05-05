package aiss.githubminer.service;

import aiss.githubminer.model.gitminer.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitMinerService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${gitminer.baseuri}")
    private String gitMinerUri;

    public void createProject(Project project) {
        String uri = gitMinerUri + "/projects";
        ResponseEntity<String> response = restTemplate.postForEntity(uri, project, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error enviando datos a GitMiner: " + response.getStatusCode());
        }
    }
}

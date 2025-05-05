package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.gitMiner.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitMinerService {
    @Value("${gitminer.baseuri}")
    private String gitMinerUri;

    @Autowired
    private RestTemplate restTemplate;

    public void createProject(Project project) {
        HttpHeaders headers = new HttpHeaders(); // Cabecera HTTP
        headers.setContentType(MediaType.APPLICATION_JSON); // Especificamos JSON como tipo de contenido

        HttpEntity<Project> request = new HttpEntity<>(project, headers); // Cuerpo de la petición

        ResponseEntity<String> response = restTemplate.exchange(
                gitMinerUri + "/projects",
                HttpMethod.POST,
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error enviando datos a GitMiner: " + response.getStatusCode());
        }
    }
}

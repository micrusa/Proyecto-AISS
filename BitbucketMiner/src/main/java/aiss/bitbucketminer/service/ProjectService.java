package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.gitMiner.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
        @Autowired
        RestTemplate restTemplate;
        @Value("${bitbucket.baseUri}")
        public String baseUri;

        String token = "ATBBvFTbExAruQghMU6RkkJ9qHwa06C66165";

        /*String baseUri = "https://api.bitbucket.org/";*/

        public List<Project> getProjects(String workspace) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            String uri = baseUri +"/workspaces/"+ workspace;
            Project[] projects = restTemplate.getForObject(uri, Project[].class);

            return Arrays.asList(projects);
        }


    public Project getProject(String workspace) {
        String uri = baseUri + "/workspaces/" + workspace + "/projects";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);
        List<Map<String, Object>> values = (List<Map<String, Object>>) response.get("values");

        if (values == null || values.isEmpty()) return null;

        Map<String, Object> firstProject = values.get(0);

        Project project = new Project();
        project.setId((String) firstProject.get("uuid"));
        project.setName((String) firstProject.get("name"));
        project.setWebUrl((String) firstProject.get("webUrl"));
        // Agrega más setters si los tienes en tu modelo

        return project;
    }
        public Project create(String owner, String repo, Project project) {
            String uri = baseUri + owner + "/" + repo + "/projects";
            Project createdProject = restTemplate.postForObject(uri, project, Project.class);
            return createdProject;

        }

}

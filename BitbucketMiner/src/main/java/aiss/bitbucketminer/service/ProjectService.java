package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.gitMiner.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProjectService {
        @Autowired
        BitbucketService bitbucketService;

    /*public List<Project> getProjects(String workspace) {
        String uri = baseUri + "/workspaces/" + workspace + "/projects";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);
        List<Project> projects = new ArrayList<>();

        if (response != null && response.containsKey("values")) {
            List<Map<String, Object>> values = (List<Map<String, Object>>) response.get("values");

            for (Map<String, Object> item : values) {
                Project p = new Project();
                p.setId((String) item.get("key")); // O usa name, según tu modelo
                p.setName((String) item.get("name"));

                // Obtener URL del proyecto si quieres
                Map<String, Map<String, String>> links = (Map<String, Map<String, String>>) item.get("links");
                if (links != null && links.containsKey("html")) {
                    p.setWebUrl(links.get("html").get("href"));
                }
            }
        }
        return projects;
    }*/



    public Project getProject(String workspace, String projectKey) {
        String uri = "workspaces/" + workspace + "/projects/" + projectKey;

        return bitbucketService.getForAuthenticated(uri, Project.class);
    }
        /*public Project create(String owner, String repo, Project project) {
            String uri = baseUri + owner + "/" + repo + "/projects";
            Project createdProject = restTemplate.postForObject(uri, project, Project.class);
            return createdProject;

        }
*/
}

package aiss.githubminer.service;

import aiss.githubminer.model.github.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    public Project getProject(String id) {
        String uri = baseuri + id;
        Project projectDetails = restTemplate.getForObject(uri, Project.class);
        return projectDetails;
    }

    public List<Project> getProjects(String owner, String repo){
        String uri = baseuri + owner + "/" + repo + "/projects";
        Project[] projects = restTemplate.getForObject(uri, Project[].class);
        return List.of(projects);
    }

    public Project createProject(String owner, String repo, Project project) {
        String uri = baseuri + owner + "/" + repo + "/projects";
        Project createdProject = restTemplate.postForObject(uri, project, Project.class);
        return createdProject;
    }

}

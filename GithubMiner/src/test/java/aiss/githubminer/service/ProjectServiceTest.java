package aiss.githubminer.service;

import aiss.githubminer.model.github.project.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Get project by ID")
    void getProject() {
        String id = "123456789";
        Project project = projectService.getProject(id);
        assertNotNull(project);
        assertEquals(id, project.getId());
        System.out.println(project);
    }

    @Test
    @DisplayName("Get all projects")
    void getProjects() {
        List<Project> projects = projectService.getProjects("spring-projects", "spring-framework");
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    @Test
    void createProject() {
    }
}
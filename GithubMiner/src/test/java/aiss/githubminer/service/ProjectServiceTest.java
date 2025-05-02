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
    @DisplayName("Get project")
    void getProject() {
        Project project = projectService.getProject("spring-projects", "spring-boot");
        assertNotNull(project);
        System.out.println(project);
    }
}
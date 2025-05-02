package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.gitMiner.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Get Project")
    void getProject() {
        Project project = projectService.getProject("migboibor", "P1");
        assertNotNull(project);
        System.out.println(project);
    }


    /*@Test
    void create() {
        Project project = projectService.create("spring-projects", "spring-framework", new Project());
        assertNotNull(project, "Project nulo");
        System.out.println("Project: " + project);
    }*/
}
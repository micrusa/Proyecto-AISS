package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.bitBucket.project.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;
    //https://bitbucket.org/djkool14/workspace/projects/SC
//gentlero/bitbucket-api
    @Test
    @DisplayName("Get Project")
    void getProject() {
        Project project = projectService.getProject("gentlero", "OSS");
        assertNotNull(project);
        System.out.println(project);
    }

}
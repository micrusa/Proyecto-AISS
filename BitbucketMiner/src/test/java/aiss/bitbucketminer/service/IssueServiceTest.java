package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.bitBucket.issue.Issue;
import aiss.bitbucketminer.model.bitBucket.project.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class IssueServiceTest {

    @Autowired
    IssueService issueService;

//https://bitbucket.org/djkool14/workspace/projects/SC
    @Test
    void getAllIssues() {
        List<Issue> issues = issueService.getAllIssues("gentlero", "bitbucket-api", 5, 2);
        assertNotNull(issues);
        System.out.println(issues);
    }


}
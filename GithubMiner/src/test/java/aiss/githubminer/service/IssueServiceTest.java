package aiss.githubminer.service;

import aiss.githubminer.model.github.issue.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Test
    void getAllIssues() {
        List<Issue> issues = issueService.getAllIssues("spring-projects", "spring-framework");
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        System.out.println(issues);
    }

    @Test
    void getIssue() {
    }

    @Test
    void getIssuesByUser() {
    }

    @Test
    void getIssuesByState() {
    }

    @Test
    void getIssuesComments() {
    }
}
package aiss.githubminer.service;

import aiss.githubminer.model.github.issue.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Test
    void getAllIssues() {
        List<Issue> issues = issueService.getAllIssues("octocat", "Hello-World", 1, 5);
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        System.out.println(issues);
    }

    @Test
    void getIssue() {
        Issue issue = issueService.getIssue("octocat", "Hello-World", "3918");
        assertNotNull(issue);
        System.out.println(issue);
    }

}
package aiss.githubminer.service;

import aiss.githubminer.model.github.commit.Commit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {


    @Autowired
    CommitService commitService;

    @Test
    void getCommits() {
        List<Commit> commits = commitService.getCommits("spring-projects", "spring-framework", 90, 5);
        assertNotNull(commits);
        assertFalse(commits.isEmpty());
        System.out.println(commits);
    }

}
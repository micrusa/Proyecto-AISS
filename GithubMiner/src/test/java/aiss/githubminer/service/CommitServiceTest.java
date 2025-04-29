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
        List<Commit> commits = commitService.getCommits("spring-projects", "spring-framework");
        assertNotNull(commits);
        assertFalse(commits.isEmpty());
        System.out.println(commits);
    }

    @Test
    void getCommit() {
        Commit commit = commitService.getCommit("spring-projects", "spring-framework", "b98ef1309bdeda0f1177b93becc19c7b8ba66c35");
        assertNotNull(commit);
        assertEquals("b98ef1309bdeda0f1177b93becc19c7b8ba66c35", commit.getSha());
        System.out.println(commit);
    }

}
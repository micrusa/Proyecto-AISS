package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.bitBucket.commit.Commit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class CommitServiceTest {

    @Autowired
    CommitService commitService;

    @Test
    @DisplayName("Get Commit")
    void getCommit() {
        Commit commit = commitService.getCommit("migboibor", "prueba2", "85c93c7f99da23fca2fa1a8fcb0219e805cb2c81");
        assertNotNull(commit);
        System.out.println(commit);
    }

    @Test
    @DisplayName("Get commits")
    void getCommits() {
        List<Commit> commits = commitService.getCommits("migboibor", "prueba2");
        assertNotNull(commits);
        assertFalse(commits.isEmpty());
        System.out.println(commits);
    }
}
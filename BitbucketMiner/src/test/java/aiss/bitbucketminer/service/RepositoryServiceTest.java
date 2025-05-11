package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.bitBucket.repositories.Repository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class RepositoryServiceTest {

    @Autowired
    RepositoryService repositoryService;

    @Test
    @DisplayName("Get Repository")
    void getRepository() {
        Repository repository = repositoryService.getRepository("gentlero", "bitbucket-api");
        assertNotNull(repository);
        System.out.println(repository);
    }
  
}
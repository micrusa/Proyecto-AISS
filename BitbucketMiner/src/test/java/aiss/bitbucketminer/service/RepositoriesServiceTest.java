package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.bitBucket.repositories.Repositories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BitbucketminerApplication.class)
class RepositoriesServiceTest {

    @Autowired
    RepositoriesService repositoriesService;

    @Test
    void getAllRepositories() {
        Repositories repositories = repositoriesService.getRepository("gentlero", "bitbucket-api");
        assertNotNull(repositories);
        System.out.println(repositories);
    }
  
}
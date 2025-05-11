package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    @Autowired
    BitbucketService bitbucketService;

    public Repository getRepository(String workspace, String repo_slug) {

            String currentUri = "repositories/" + workspace + "/" + repo_slug;
            Repository repository = bitbucketService.getForAuthenticated(currentUri, Repository.class);
            return repository;


    }
}

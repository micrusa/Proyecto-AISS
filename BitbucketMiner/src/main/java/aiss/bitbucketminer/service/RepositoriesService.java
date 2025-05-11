package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoriesService {

    @Autowired
    BitbucketService bitbucketService;

    public Repositories getRepository(String workspace, String repo_slug) {

            String currentUri = "repositories/" + workspace + "/" + repo_slug;
            Repositories repository = bitbucketService.getForAuthenticated(currentUri, Repositories.class);
            return repository;


    }
}

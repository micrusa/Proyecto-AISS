package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.commit.Commit;
import aiss.bitbucketminer.model.bitBucket.commit.CommitContainer;
import aiss.bitbucketminer.model.bitBucket.repositories.Repositories;
import aiss.bitbucketminer.model.bitBucket.repositories.RepositoriesContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoriesService {

    @Autowired
    BitbucketService bitbucketService;


    /*public Project getProject(String workspace, String projectKey) {
        String uri = "workspaces/" + workspace + "/projects/" + projectKey;

        return bitbucketService.getForAuthenticated(uri, Project.class);
    }*/
    public List<Repositories> getAllRepositories(String workspace, String repo_slug, int maxPages) {

        List<Repositories> allRepositories = new ArrayList<>();
        int page = 1;

        while (page <= maxPages) {
            // Construir la URI manualmente
            String currentUri = "repositories/" + workspace + "/" + "/" + repo_slug;

            // Usar BitbucketService para realizar la llamada autenticada
            RepositoriesContainer repositoriesContainer = bitbucketService.getForAuthenticated(currentUri, RepositoriesContainer.class);

            if (repositoriesContainer == null || repositoriesContainer.getValues().isEmpty()) {
                break;
            }

            allRepositories.addAll(repositoriesContainer.getValues());
            page++;
        }

        return allRepositories;

    }

}

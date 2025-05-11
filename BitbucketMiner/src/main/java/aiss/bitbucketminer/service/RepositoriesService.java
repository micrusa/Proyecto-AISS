package aiss.bitbucketminer.service;

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

    public List<Repositories> getAllRepositories(String workspace, String repo_slug, int maxPages) {

        List<Repositories> allRepositories = new ArrayList<>();
        int page = 1;

        while (page <= maxPages) {
            // URI corregida: se elimina la doble barra
            String currentUri = "repositories/" + workspace + "/" + repo_slug;

            // Llamada autenticada al servicio
            RepositoriesContainer repositoriesContainer = bitbucketService.getForAuthenticated(currentUri, RepositoriesContainer.class);

            // Solución rápida: comprobación adicional para evitar NullPointerException
            if (repositoriesContainer == null || repositoriesContainer.getValues() == null || repositoriesContainer.getValues().isEmpty()) {
                break;
            }

            allRepositories.addAll(repositoriesContainer.getValues());
            page++;
        }

        return allRepositories;
    }
}

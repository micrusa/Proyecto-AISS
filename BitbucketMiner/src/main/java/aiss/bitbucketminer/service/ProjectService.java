package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.bitBucket.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    BitbucketService bitbucketService;


    public Project getProject(String workspace, String projectKey) {
        String uri = "workspaces/" + workspace + "/projects/" + projectKey;

        return bitbucketService.getForAuthenticated(uri, Project.class);
    }

}

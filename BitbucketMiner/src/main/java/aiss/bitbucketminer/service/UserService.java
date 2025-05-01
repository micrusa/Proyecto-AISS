package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.gitMiner.Project;
import aiss.bitbucketminer.model.gitMiner.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.baseUri}")
    public String baseUri;


    String token = "ATBBvFTbExAruQghMU6RkkJ9qHwa06C66165";

    public List<User> getUsers() {

        User[] users = restTemplate.getForObject(baseUri, User[].class);

        return Arrays.asList(users);
    }

    public User getUser(String id) {
        String uri = baseUri + id;
        User user = restTemplate.getForObject(uri, User.class);

        return user;
    }

  /*  public User create() {
        User createdUser = restTemplate.postForObject(uri, project, Project.class);
        return createdUser;

    }*/

}


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
    @Value("${bitbucket.token")
    public String token;

    public User getCurrentUser() {
        String uri = baseUri + "/user";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        User user = restTemplate.getForObject(uri, User.class);

        return user;
    }

    public User getUserById(String userId) {
        String uri = baseUri + "/users/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        User user = restTemplate.getForObject(uri, User.class);

        return user;
    }




}


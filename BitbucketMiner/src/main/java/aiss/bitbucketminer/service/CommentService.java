package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.gitMiner.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.baseUri}")
    public String baseUri;
    @Value("${bitbucket.token}")
    public String token;

    public List<Comment> getComments(String workspace, String repo_slug, String commit) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + workspace + "/" + repo_slug + "/commit/" + commit + "/comments";
        Comment[] comments = restTemplate.getForObject(uri, Comment[].class);
        return List.of(comments);
    }

    public Comment getComment(String workspace, String repo_slug,String commit, String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + workspace + "/" + repo_slug + "/commit/" + commit + "/comments/" + id;
        Comment comment = restTemplate.getForObject(uri, Comment.class);
        return comment;
    }






}

package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.gitMiner.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    String token = "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";

    String baseUri = "https://api.bitbucket.org/2.0/repositories/";

    public List<Comment> getComments(String owner, String repo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + owner + "/" + repo + "/comments";
        Comment[] comments = restTemplate.getForObject(uri, Comment[].class);
        return List.of(comments);
    }

    public Comment getCommentById(String owner, String repo, String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String uri = baseUri + owner + "/" + repo + "/comments/" + id;
        Comment comment = restTemplate.getForObject(uri, Comment.class);
        return comment;
    }






}

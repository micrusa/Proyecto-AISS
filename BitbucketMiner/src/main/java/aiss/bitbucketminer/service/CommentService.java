package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.bitBucket.comment.Comment;
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
    BitbucketService bitbucketService;


    public List<Comment> getComments(String workspace, String repo_slug, String commit) {


        String uri = "repositories/" + workspace + "/" + repo_slug + "/commit/" + commit + "/comments";
        Comment[] comments = bitbucketService.getForAuthenticated(uri, Comment[].class);
        return List.of(comments);
    }

    public Comment getComment(String workspace, String repo_slug,String commit, String id) {

        String uri = workspace + "/" + repo_slug + "/commit/" + commit + "/comments/" + id;
        Comment comment = bitbucketService.getForAuthenticated(uri, Comment.class);
        return comment;
    }

}

package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.bitBucket.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    BitbucketService bitbucketService;

    public List<Comment> getComments(String workspace, String repo_slug, String commit) {
        Comment[] comments = bitbucketService.getForAuthenticated(
                workspace + "/" + repo_slug + "/commit/" + commit + "/comments",
                Comment[].class
        );
        return List.of(comments);
    }

    public Comment getComment(String workspace, String repo_slug,String commit, String id) {
        Comment comment = bitbucketService.getForAuthenticated(
                workspace + "/" + repo_slug + "/commit/" + commit + "/comments/" + id,
                Comment.class
        );
        return comment;
    }






}

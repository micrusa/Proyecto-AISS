package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.bitBucket.comment.Comment;
import aiss.bitbucketminer.model.bitBucket.comment.CommentContainer;
import aiss.bitbucketminer.model.bitBucket.commit.CommitContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    BitbucketService bitbucketService;


    public List<Comment> getComments(String workspace, String repo_slug, String commit) {


        String uri = "repositories/" + workspace + "/" + repo_slug + "/commit/" + commit + "/comments";
        CommentContainer comments = bitbucketService.getForAuthenticated(uri, CommentContainer.class);
        return comments.getValues();
    }

    public Comment getComment(String workspace, String repo_slug,String commit, String id) {

        String uri = workspace + "/" + repo_slug + "/commit/" + commit + "/comments/" + id;
        Comment comment = bitbucketService.getForAuthenticated(uri, Comment.class);
        return comment;
    }

}

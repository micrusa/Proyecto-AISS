package aiss.bitbucketminer.service;


import aiss.bitbucketminer.model.bitBucket.comment.Comment;
import aiss.bitbucketminer.model.bitBucket.comment.CommentContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    BitbucketService bitbucketService;


    public List<Comment> getComments(String workspace, String repo_slug, String issueId, int maxPages) {

        List<Comment> allComments = new ArrayList<>();
        int page = 1;

        while (page <= maxPages) {
            String currentUri = "repositories/" + workspace + "/" + repo_slug + "/issues/" + issueId + "/comments?page=" + page;

            CommentContainer commentContainer = bitbucketService.getForAuthenticated(currentUri, CommentContainer.class);

            if (commentContainer == null || commentContainer.getValues().isEmpty()) {
                break;
            }

            allComments.addAll(commentContainer.getValues());
            page++;
        }

        return allComments;
    }

}

package aiss.githubminer.service;

import aiss.githubminer.model.github.comment.Comment;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    GithubService githubService;

    @Operation(
            summary = "Retrieve all comments",
            description = "Get all comment objects by owner and repo",
            tags = {"comments", "get"}
    )
    public List<Comment> getComments(String owner, String repo, Integer number) {
        String uri = owner + "/" + repo + "/comments";
        Comment[] comments = githubService.getAuthenticated(uri, Comment[].class).getBody();
        return List.of(comments);
    }
}

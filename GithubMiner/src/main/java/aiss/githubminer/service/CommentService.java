package aiss.githubminer.service;

import aiss.githubminer.model.github.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.baseuri}")
    public String baseuri;

    public List<Comment> getComments(String owner, String repo) {
        String uri = baseuri + owner + "/" + repo + "/comments";
        Comment[] comments = restTemplate.getForObject(uri, Comment[].class);
        return List.of(comments);
    }
}

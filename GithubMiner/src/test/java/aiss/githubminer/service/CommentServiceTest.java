package aiss.githubminer.service;

import aiss.githubminer.model.github.comment.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void getComments() {
        List<Comment> comments = commentService.getComments("spring-projects", "spring-framework");
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }
}
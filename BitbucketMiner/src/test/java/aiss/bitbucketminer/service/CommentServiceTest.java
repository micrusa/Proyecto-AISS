package aiss.bitbucketminer.service;

import aiss.bitbucketminer.BitbucketminerApplication;
import aiss.bitbucketminer.model.gitMiner.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = BitbucketminerApplication.class)
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get commit's Comments")
    void getComments() {
        List<Comment> comments = commentService.getComments("migboibor", "prueba2", "85c93c7f99da23fca2fa1a8fcb0219e805cb2c81");
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        System.out.println(comments);

    }

    @Test
    void getComment() {
        Comment comment = commentService.getComment("migboibor", "prueba2", "85c93c7", "");
        assertNotNull(comment);
        System.out.println(comment);
    }
}
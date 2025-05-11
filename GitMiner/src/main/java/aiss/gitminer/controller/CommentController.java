package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Comment", description = "Comment Management API")
@RestController
@RequestMapping("/gitminer/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Operation(
            summary = "Retrieve all comments",
            description = "Get all comment objects",
            tags = {"comments", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Comment.class, type = "array"))
            }),
    })
    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return ResponseEntity.ok(comments);
    }

    @Operation(
            summary = "Retrieve comment by ID",
            description = "Get comment object by specifying its ID",
            tags = {"comments", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Comment.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getById(
            @Parameter(description = "ID of the comment to be retrieved", required = true) @PathVariable(name = "id") String id
    ) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        Comment existingComment = comment.orElseThrow(CommentNotFoundException::new);
        return ResponseEntity.ok(existingComment);
    }
}

package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Issue", description = "Issue Management API")
@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {
    @Autowired
    private IssueRepository issueRepository;

    @Operation(
            summary = "Retrieve all issues",
            description = "Get all issue objects by state and author ID",
            tags = {"issues", "get"}
    )
    @GetMapping
    public ResponseEntity<List<Issue>> getAll(
            @Parameter(description = "The state of the issues to be retrieved")
            @RequestParam(name = "state", required = false) String state,
            @Parameter(description = "The author ID of the issues to be retrieved")
            @RequestParam(name = "authorId", required = false) String authorId
    ) {
        List<Issue> issues = issueRepository.findByStateAndAuthorId(state, authorId);
        return ResponseEntity.ok(issues);
    }

    @Operation(
            summary = "Retrieve issue by ID",
            description = "Get issue object by specifying its ID",
            tags = {"issues", "get"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getById(
            @Parameter(description = "ID of the issue to be retrieved", required = true) @PathVariable(name = "id") String id
    ) throws IssueNotFoundException {
        Optional<Issue> issue = issueRepository.findById(id);
        Issue existingIssue = issue.orElseThrow(IssueNotFoundException::new);
        return ResponseEntity.ok(existingIssue);
    }

    @Operation(
            summary = "Retrieve comments of an issue by ID",
            description = "Get all comments of an issue by specifying its ID",
            tags = {"issues", "comments", "get"}
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(
            @Parameter(description = "ID of the issue to retrieve comments from", required = true) @PathVariable(name = "id") String id
    ) throws IssueNotFoundException {
        Optional<Issue> issue = issueRepository.findById(id);
        Issue existingIssue = issue.orElseThrow(IssueNotFoundException::new);
        List<Comment> comments = existingIssue.getComments();
        return ResponseEntity.ok(comments);
    }

    @Operation(
            summary = "Retrieve issues by author ID",
            description = "Get all issues by specifying its author ID",
            tags = {"issues", "get"}
    )

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Issue>> getByAuthorId(
            @Parameter(description = "ID of the author whose issues to be retrieved", required = true) @PathVariable(name = "id") String id
    ) {
        List<Issue> issues = issueRepository.findByStateAndAuthorId(null, id);
        return ResponseEntity.ok(issues);

    }

    @Operation(
            summary = "Retrieve issues by state",
            description = "Get all issues by specifying its state",
            tags = {"issues", "get"}
    )
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Issue>> getByState(
            @Parameter(description = "State of the issues to be retrieved", required = true) @PathVariable(name = "state") String state
    ) {
        List<Issue> issues = issueRepository.findByStateAndAuthorId(state, null);
        return ResponseEntity.ok(issues);
    }



}

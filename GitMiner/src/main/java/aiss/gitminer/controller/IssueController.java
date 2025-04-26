package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {
    @Autowired
    private IssueRepository issueRepository;

    @GetMapping
    public ResponseEntity<List<Issue>> getAll(
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "authorId", required = false) String authorId
    ) {
        List<Issue> issues = issueRepository.findByStateAndAuthorId(state, authorId);
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getById(
            @PathVariable(name = "id") String id
    ) throws IssueNotFoundException {
        Optional<Issue> issue = issueRepository.findById(id);
        Issue existingIssue = issue.orElseThrow(IssueNotFoundException::new);
        return ResponseEntity.ok(existingIssue);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(
            @PathVariable(name = "id") String id
    ) throws IssueNotFoundException {
        Optional<Issue> issue = issueRepository.findById(id);
        Issue existingIssue = issue.orElseThrow(IssueNotFoundException::new);
        List<Comment> comments = existingIssue.getComments();
        return ResponseEntity.ok(comments);
    }

}

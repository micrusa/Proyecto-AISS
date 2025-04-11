package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {
    @GetMapping
    public ResponseEntity<List<Issue>> getAll(
            @RequestParam(name = "state") @Nullable String state,
            @RequestParam(name = "authorId") @Nullable String authorId
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getById(
            @PathVariable(name = "id") String id
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(
            @PathVariable(name = "id") String id
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

}

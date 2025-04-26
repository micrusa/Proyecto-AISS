package aiss.gitminer.controller;

import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {
    @Autowired
    private CommitRepository commitRepository;

    @GetMapping
    public ResponseEntity<List<Commit>> getAll(
            @PathVariable(name = "email", required = false) String email
    ) {
        List<Commit> commits;
        if(email == null) {
            commits = commitRepository.findAll();
        } else {
            commits = commitRepository.findByEmail(email);
        }
        return ResponseEntity.ok(commits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commit> getById(
            @PathVariable(name = "id") String id
    ) throws CommitNotFoundException {
        Optional<Commit> commit = commitRepository.findById(id);
        Commit existingCommit = commit.orElseThrow(CommitNotFoundException::new);
        return ResponseEntity.ok(existingCommit);
    }
}

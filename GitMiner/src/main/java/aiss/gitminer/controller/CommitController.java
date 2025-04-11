package aiss.gitminer.controller;

import aiss.gitminer.model.Commit;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {
    @GetMapping
    public ResponseEntity<List<Commit>> getAll(
            @PathVariable(name = "email") @Nullable String email
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commit> getById(
            @PathVariable(name = "id") String id
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }
}

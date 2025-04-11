package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @GetMapping
    public ResponseEntity<List<Project>> getAll() { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(
            @PathVariable(name = "id") String id
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @PostMapping
    public ResponseEntity<Project> create(
            @RequestBody @NonNull Project project
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }
}

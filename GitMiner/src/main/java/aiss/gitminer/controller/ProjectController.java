package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @GetMapping
    public String getAll() { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @GetMapping("/{id}")
    public String getById(
            @PathVariable(name = "id") String id
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @PostMapping
    public String create(
            @RequestBody @NonNull Project project
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }
}

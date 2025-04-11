package aiss.gitminer.controller;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @GetMapping
    public String getAll(
            @RequestParam(name = "state") @Nullable String state
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }

    @GetMapping("/{id}")
    public String getById(
            @PathVariable(name = "id") String id
    ) { // TODO
        throw new UnsupportedOperationException("Por implementar");
    }
}

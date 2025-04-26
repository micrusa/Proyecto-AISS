package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(
            @PathVariable(name = "id") String id
    ) throws ProjectNotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        Project existingProject = project.orElseThrow(ProjectNotFoundException::new);
        return ResponseEntity.ok(existingProject);
    }

    @PostMapping
    public ResponseEntity<Project> create(
            @RequestBody Project project
    ) {
        Project createdProject = projectRepository.save(project);
        return ResponseEntity.ok(createdProject);
    }
}

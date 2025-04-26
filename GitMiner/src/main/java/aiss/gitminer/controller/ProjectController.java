package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Project", description = "Project Management API")
@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Operation(
            summary = "Retrieve all projects",
            description = "Get all project objects",
            tags = {"projects", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class, type = "array"))
            }),
    })
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }

    @Operation(
            summary = "Retrieve project by ID",
            description = "Get project object by specifying its ID",
            tags = {"projects", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(
            @Parameter(description = "ID of the project to be retrieved") @PathVariable(name = "id") String id
    ) throws ProjectNotFoundException {
        Optional<Project> project = projectRepository.findById(id);
        Project existingProject = project.orElseThrow(ProjectNotFoundException::new);
        return ResponseEntity.ok(existingProject);
    }

    @Operation(
            summary = "Create project",
            description = "Create a new project object",
            tags = {"projects", "post"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Project> create(
            @Parameter(description = "Project to be created") @RequestBody Project project
    ) {
        Project createdProject = projectRepository.save(project);
        return ResponseEntity.ok(createdProject);
    }
}

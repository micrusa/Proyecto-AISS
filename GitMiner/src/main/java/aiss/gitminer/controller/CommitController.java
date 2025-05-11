package aiss.gitminer.controller;

import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Commit", description = "Commit Management API")
@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {
    @Autowired
    private CommitRepository commitRepository;

    @Operation(
            summary = "Retrieve all commits",
            description = "Get all commit objects by email",
            tags = {"commits", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Commit.class, type = "array"))
            })
    })
    @GetMapping
    public ResponseEntity<List<Commit>> getAll(
            @Parameter(description = "The email of the commits to be retrieved") @PathVariable(name = "email", required = false) String email
    ) {
        List<Commit> commits;
        if(email == null) {
            commits = commitRepository.findAll();
        } else {
            commits = commitRepository.findByAuthorEmail(email);
        }
        return ResponseEntity.ok(commits);
    }

    @Operation(
            summary = "Retrieve commit by ID",
            description = "Get commit object by specifying its ID",
            tags = {"commits", "get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Commit.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Commit> getById(
            @Parameter(description = "ID of the commit to be retrieved", required = true) @PathVariable(name = "id") String id
    ) throws CommitNotFoundException {
        Optional<Commit> commit = commitRepository.findById(id);
        Commit existingCommit = commit.orElseThrow(CommitNotFoundException::new);
        return ResponseEntity.ok(existingCommit);
    }
}

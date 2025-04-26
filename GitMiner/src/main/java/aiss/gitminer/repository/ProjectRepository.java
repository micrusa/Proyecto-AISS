package aiss.gitminer.repository;

import aiss.gitminer.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, String> {
}

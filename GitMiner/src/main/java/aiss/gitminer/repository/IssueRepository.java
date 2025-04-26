package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {
    @Query("""
            FROM Issue i
            WHERE (:state is null or i.state = :state)
                and (:authorId is null or i.author.id = :authorId)
            """
    )
    List<Issue> findByStateAndAuthorId(String state, String authorId);
}

package czar.seguidino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueHistory;

@Repository("issueHistoryRepository")
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long> {

    List<IssueHistory> findAllByIdIssueOrderByCreationAsc(Long idIssue);
}

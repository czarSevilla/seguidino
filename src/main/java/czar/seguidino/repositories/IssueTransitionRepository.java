package czar.seguidino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueTransition;

@Repository("issueTransitionRepository")
public interface IssueTransitionRepository extends JpaRepository<IssueTransition, Long> {
    
    List<IssueTransition> findTop2ByIdIssueOrderByCreationDesc(Long idIssue);
    
    List<IssueTransition> findAllByIdIssueOrderByCreation(Long idIssue);
}

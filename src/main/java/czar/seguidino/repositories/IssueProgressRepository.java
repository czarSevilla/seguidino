package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueProgress;

@Repository("issueProgressRepository")
public interface IssueProgressRepository extends JpaRepository<IssueProgress, Long> {

    @Query("from IssueProgress where idIssue = ? and stop is null")
    IssueProgress getOneRunningByIdIssue(Long idIssue);
}

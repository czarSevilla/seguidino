package czar.seguidino.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueAccess;
import czar.seguidino.entities.Issue;

@Repository("issueAccessRepository")
public interface IssueAccessRepository extends JpaRepository<IssueAccess, Long> {
    IssueAccess findOneByIdUserAndIssue(Long idUser, Issue issue);
    
    @Query("select issue from IssueAccess ia left join ia.issue issue where ia.idUser = ?")
    Page<Issue> findIssuesByIdUser(Long idUser, Pageable pageable);
}

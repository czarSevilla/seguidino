package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueStatus;

@Repository("issueStatusRepository")
public interface IssueStatusRepository extends JpaRepository<IssueStatus, Long> {

}

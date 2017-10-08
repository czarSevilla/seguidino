package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueResolution;

@Repository("issueResolutionRepository")
public interface IssueResolutionRepository extends JpaRepository<IssueResolution, Long> {

}

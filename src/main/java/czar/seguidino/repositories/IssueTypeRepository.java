package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssueType;

@Repository("issueTypeRepository")
public interface IssueTypeRepository extends JpaRepository<IssueType, Long> {

}

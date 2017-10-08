package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.IssuePriority;

@Repository("issuePriorityRepository")
public interface IssuePriorityRepository extends JpaRepository<IssuePriority, Long> {

}

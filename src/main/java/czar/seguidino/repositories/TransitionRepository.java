package czar.seguidino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Transition;

@Repository("transitionRepository")
public interface TransitionRepository extends JpaRepository<Transition, Long> {
    
    List<Transition> findAllByIdIssueStatusFromAndIdRolIn(Long idIssueStatusFrom, List<Long> idsRoles);
}

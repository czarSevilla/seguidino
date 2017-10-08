package czar.seguidino.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.ProjectComponent;

@Repository("projectComponentRepository")
public interface ProjectComponentRepository extends JpaRepository<ProjectComponent, Long> {
    List<ProjectComponent> findByIdProject(Long idProject);
    Set<ProjectComponent> findByidComponentIn(List<Long> idsComponents);
}

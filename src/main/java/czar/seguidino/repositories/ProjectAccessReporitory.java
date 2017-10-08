package czar.seguidino.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Project;
import czar.seguidino.entities.ProjectAccess;

@Repository("projectAccessReporitory")
public interface ProjectAccessReporitory extends JpaRepository<ProjectAccess, Long> {

    ProjectAccess findOneByIdUserAndProject(Long idUser, Project project);
    
    @Query("select p from ProjectAccess pa left join pa.project p where pa.idUser = ?")
    Page<Project> findProjectsByIdUser(Long idUser, Pageable pageable);
    
}

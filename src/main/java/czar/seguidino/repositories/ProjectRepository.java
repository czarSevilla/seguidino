package czar.seguidino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Project;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findOneByKeyProject(String keyProyect);
    
    @Query("select p from Project p where p.idProject in (select pm.idProject from ProjectMember pm where pm.user.idUser = ?) order by p.idProject desc")
    List<Project> findByIdUser(Long idUser);
    
    @Query("select p.idProject from Project p where p.keyProject = ?")
    Long getIdProjectByKeyProject(String keyProject);
}

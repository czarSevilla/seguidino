package czar.seguidino.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.ProjectMember;
import czar.seguidino.entities.ProjectRole;

@Repository("projectMemberRepository")
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    @Query("select pm from ProjectMember pm where pm.idProject = :idProject and pm.role in (:roles)")
    List<ProjectMember> findByIdProjectAndRoles(@Param("idProject") Long idProject, @Param("roles") Collection<ProjectRole> roles);
    
    @Query("select pr from ProjectMember pm join pm.role pr where pm.idProject = ? and pm.user.idUser = ?")
    List<ProjectRole> findByIdProjectAndUser(Long idProject, Long idUser);
}

package czar.seguidino.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Activity;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findAllByIdProject(Long idProject, Pageable pageable);
    
    @Query("select a from Activity a where a.issue.idIssue = ? order by a.creation desc, a.idActivity desc ")
    List<Activity> findAllByIdIssue(Long idIssue);
}

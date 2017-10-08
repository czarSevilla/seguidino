package czar.seguidino.services;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import czar.seguidino.entities.Activity;

public interface ActivityService {
    
    Page<Activity> findAllByKeyProject(String keyProject, Pageable pageable);
    
    List<Activity> findAllByIdIssue(Long idIssue);
}

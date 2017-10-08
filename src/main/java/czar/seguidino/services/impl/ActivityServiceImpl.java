package czar.seguidino.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import czar.seguidino.entities.Activity;
import czar.seguidino.repositories.ActivityRepository;
import czar.seguidino.repositories.ProjectRepository;
import czar.seguidino.services.ActivityService;
import czar.seguidino.utils.HumanizeTimeUtils;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Override
    public Page<Activity> findAllByKeyProject(String keyProject, Pageable pageable) {
        Long idProject = projectRepository.getIdProjectByKeyProject(keyProject);
        Page<Activity> page = activityRepository.findAllByIdProject(idProject, pageable);
        for (Activity activity : page.getContent()) {
            activity.setCreationString(HumanizeTimeUtils.humanizedTime(activity.getCreation()));
        }
        return page;
    }

    @Override
    public List<Activity> findAllByIdIssue(Long idIssue) {
        List<Activity> activities = activityRepository.findAllByIdIssue(idIssue);
        for (Activity activity : activities) {
            activity.setCreationString(HumanizeTimeUtils.humanizedTime(activity.getCreation()));
        }
        return activities;
    }

}

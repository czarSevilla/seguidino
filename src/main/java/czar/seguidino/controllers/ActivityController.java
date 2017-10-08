package czar.seguidino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import czar.seguidino.entities.Activity;
import czar.seguidino.services.ActivityService;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;
    
    @RequestMapping("/{keyProject}/list")
    public String list(Model model, @PathVariable(value = "keyProject") String keyProject) {
        Pageable pageRequest = new PageRequest(0, 10, Sort.Direction.DESC, "creation");
        Page<Activity> page = activityService.findAllByKeyProject(keyProject, pageRequest); 
        model.addAttribute("activities", page.getContent());
        return "activities/list :: activities";
    }
}

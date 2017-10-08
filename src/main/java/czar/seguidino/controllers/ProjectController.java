
package czar.seguidino.controllers;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import czar.seguidino.dtos.FileDto;
import czar.seguidino.dtos.ResumeGridDto;
import czar.seguidino.entities.Activity;
import czar.seguidino.entities.Project;
import czar.seguidino.entities.ProjectComponent;
import czar.seguidino.services.ActivityService;
import czar.seguidino.services.ProjectService;
import czar.seguidino.services.ResumeGridService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
    private Logger logger = LoggerFactory.getLogger(ProjectController.class);
    
    private static final String KEY_PROJECT = "keyProject";
    
    private static final String PROJECT = "project";
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private ResumeGridService resumeGridService;
    
    @RequestMapping("/{keyProject}")
    public String show(@PathVariable(required = true) String keyProject) {
        return String.format("redirect:/projects/%s/overview/summary", keyProject);
    }
    
    @RequestMapping("/{keyProject}/overview")
    public String overview(@PathVariable(required = true) String keyProject) {
        return String.format("redirect:/projects/%s/overview/summary", keyProject);
    }
    
    @RequestMapping("/{keyProject}/overview/summary")
    public String summary(@PathVariable(required = true) String keyProject, Model model, Principal principal) {
        logger.info(String.format("username=%s", principal.getName()));
        Project project = projectService.findByKey(keyProject, principal.getName());
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "creation");
        Page<Activity> activities = activityService.findAllByKeyProject(keyProject, pageable);
        model.addAttribute("activities", activities);
        model.addAttribute(KEY_PROJECT, keyProject);
        model.addAttribute(PROJECT, project);
        return "projects/summary";
    }
    
    @RequestMapping("/{keyProject}/overview/issues")
    public String issues(@PathVariable(required = true) String keyProject, Model model) {
        Project project = projectService.findByKey(keyProject);
        int total = resumeGridService.countTotalByProject(project.getIdProject());
        ResumeGridDto gridSummary = resumeGridService.buildProjectSummary(project.getIdProject(), total);
        int unresolved = resumeGridService.countUnresolvedByProject(project.getIdProject());
        ResumeGridDto gridPriority = resumeGridService.buildGridByPriority(project.getIdProject(), unresolved);
        ResumeGridDto gridAssignee = resumeGridService.buildGridByAssignee(project.getIdProject(), unresolved);
        ResumeGridDto gridType = resumeGridService.buildGridByType(project.getIdProject(), unresolved);
        ResumeGridDto gridComponent = resumeGridService.buildGridByComponent(project.getIdProject());
        model.addAttribute("gridSummary", gridSummary);
        model.addAttribute("gridPriority", gridPriority);
        model.addAttribute("gridAssignee", gridAssignee);
        model.addAttribute("gridType", gridType);
        model.addAttribute("gridComponent", gridComponent);
        model.addAttribute(KEY_PROJECT, keyProject);
        model.addAttribute(PROJECT, project);
        return "projects/issues";
    }
    
    @RequestMapping("/{keyProject}/overview/components")
    public String components(@PathVariable(required = true) String keyProject, Model model) {
        Project project = projectService.findByKey(keyProject);
        List<ProjectComponent> components = projectService.findComponentsByProject(project.getIdProject());
        model.addAttribute(KEY_PROJECT, keyProject);
        model.addAttribute(PROJECT, project);
        model.addAttribute("components", components);
        return "projects/components";
    }
    
    @RequestMapping(value = "/{projectKey}/avatar/{file}")
    @ResponseBody
    public ResponseEntity<byte[]> showAvatar(           
            @PathVariable("projectKey") String projectKey, 
            @PathVariable("file") Long idFileReference) throws Exception {
        
        logger.info(String.format("/projects/%s/avatar/%d", projectKey, idFileReference));
        
        FileDto fileDto = projectService.findAvatar(projectKey, idFileReference);
        
        return ResponseEntity
                .ok()
                .contentType(fileDto.getContentType())
                .body(fileDto.getBytes());
    }
}

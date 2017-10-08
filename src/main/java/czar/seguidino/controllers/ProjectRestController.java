package czar.seguidino.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import czar.seguidino.dtos.OptionDto;
import czar.seguidino.services.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {
    
    @Autowired
    ProjectService projectService;
    
    @RequestMapping("/select")
    public List<OptionDto> select(Principal principal) {
        return projectService.listProjectOptions(principal.getName());
    }
    
    @RequestMapping("/{idProject}/components")
    public List<OptionDto> components(@PathVariable(name = "idProject") Long idProject) {
        return projectService.listComponentsOptions(idProject);
    }
    
    @RequestMapping("/{idProject}/assignees")
    public List<OptionDto> assignees(@PathVariable(name = "idProject") Long idProject) {
        return projectService.listAssigneesOptions(idProject);
    }
}

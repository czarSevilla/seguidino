package czar.seguidino.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import czar.seguidino.dtos.ResultDto;
import czar.seguidino.entities.Project;
import czar.seguidino.services.ProjectService;

@Controller
@RequestMapping("/admin/projects")
public class AdminProjectController {
    
    private Logger logger = LoggerFactory.getLogger(AdminProjectController.class);
    
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/{keyProject}")
    public String administration(@PathVariable(required = true) String keyProject) {
        return String.format("redirect:/admin/project/%s/components", keyProject);
    }
    
    @RequestMapping("/{keyProject}/components")
    public String adminCcomponents(@PathVariable(required = true) String keyProject, Model model) {
        Project project = projectService.findByKey(keyProject);
        model.addAttribute("keyProject", keyProject);
        model.addAttribute("project", project);
        return "projects/components";
    }
    
    @RequestMapping("/list")
    public String list(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "admin/projects/list";
    }
    
    @RequestMapping(value = "/logo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultDto> uploadLogo(
            @RequestParam("avatar") MultipartFile file, 
            @RequestParam("idProject") Long idProject) throws Exception {
        
        logger.info(String.format("uploadLogo(idProject=%d)", idProject));
        
        String url = projectService.updateAvatar(idProject, file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
        
        ResultDto result = new ResultDto();
        result.setResult("OK");
        result.setUrl(url);
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }
}

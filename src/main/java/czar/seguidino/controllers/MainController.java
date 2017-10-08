package czar.seguidino.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import czar.seguidino.dtos.MenuItem;
import czar.seguidino.services.AdminService;
import czar.seguidino.services.IssueService;
import czar.seguidino.services.ProjectService;

@Controller
public class MainController {

    @Autowired
    ProjectService projectService;
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    IssueService issueService;
    
    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }
    
    @RequestMapping("/menu")
    public String menu(Model model, Principal principal) {
        List<MenuItem> projectItems = projectService.buildProjectMenuItems(principal.getName());
        List<MenuItem> adminItems = adminService.buildAdminMenuItems(principal.getName());
        List<MenuItem> issueItems = issueService.buildIssueMenuItems(principal.getName());
        model.addAttribute("projectItems", projectItems);
        model.addAttribute("adminItems", adminItems);
        model.addAttribute("issueItems", issueItems);
        return "fragments/layout/menu :: menu";
    }
}

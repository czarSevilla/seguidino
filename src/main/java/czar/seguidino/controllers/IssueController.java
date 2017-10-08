package czar.seguidino.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import czar.seguidino.dtos.OptionDto;
import czar.seguidino.dtos.ResultDto;
import czar.seguidino.entities.Activity;
import czar.seguidino.entities.Comment;
import czar.seguidino.entities.Issue;
import czar.seguidino.entities.IssueHistory;
import czar.seguidino.entities.IssuePriority;
import czar.seguidino.entities.IssueTransition;
import czar.seguidino.entities.IssueType;
import czar.seguidino.entities.Project;
import czar.seguidino.services.ActivityService;
import czar.seguidino.services.IssueService;
import czar.seguidino.services.ProjectService;
import czar.seguidino.utils.HumanizeTimeUtils;

@Controller
@RequestMapping("/issues")
public class IssueController {
    
    private Logger logger = LoggerFactory.getLogger(IssueController.class);
    
    @Autowired
    ProjectService projectService;
    
    @Autowired
    IssueService issueService;
    
    @Autowired
    ActivityService activityService;

    @RequestMapping("/browse")
    public String browse(@RequestParam(name = "project", required = false) String keyProject,
            @RequestParam(name = "f", required = false) String filter,
            Principal principal,
            Model model) {
        
        
        if (keyProject != null) {
            Project project = projectService.findByKey(keyProject);
            if (project != null) {
                if (filter != null) {
                    Pageable pageable = new PageRequest(0, 50);
                    Page<Issue> issues = issueService.findByIdProjectAndFilter(project.getIdProject(), filter, pageable, principal.getName());
                    model.addAttribute("issues", issues.getContent());
                } else {
                    Issue issue = new Issue();
                    issue.setIdProject(project.getIdProject());
                    Page<Issue> issues = issueService.find(issue);                    
                    model.addAttribute("issues", issues.getContent());
                }
            }
        }
        return "issues/browse";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(Issue issue, @RequestParam(name = "components", required = false) List<Long> components, HttpServletResponse response) throws IOException {
        logger.info(String.format("Agregando issue... %s", issue));
        String keyIssue = issueService.save(issue, components);
        response.setContentType("application/json");
        response.getWriter().write("{\"result\":\"OK\",\"keyIssue\":\"" + keyIssue + "\"}");
    }
    
    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    public void resolve(Issue issue, @RequestParam(name = "text") String text, HttpServletResponse response, Principal principal) throws IOException {
        logger.info(String.format("Resolviendo issue... %s", issue));
        String keyIssue = issueService.resolve(issue, text, principal.getName());
        response.setContentType("application/json");
        response.getWriter().write("{\"result\":\"OK\",\"keyIssue\":\"" + keyIssue + "\"}");
    }
    
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public void close(Issue issue, @RequestParam(name = "text") String text, HttpServletResponse response, Principal principal) throws IOException {
        logger.info(String.format("Cerrando issue... %s", issue));
        String keyIssue = issueService.close(issue, text, principal.getName());
        response.setContentType("application/json");
        response.getWriter().write("{\"result\":\"OK\",\"keyIssue\":\"" + keyIssue + "\"}");
    }
    
    @RequestMapping(value = "/reopen", method = RequestMethod.POST)
    public void reopen(Issue issue, @RequestParam(name = "text") String text, HttpServletResponse response, Principal principal) throws IOException {
        logger.info(String.format("Reopening issue... %s", issue));
        String keyIssue = issueService.reopen(issue, text, principal.getName());
        response.setContentType("application/json");
        response.getWriter().write("{\"result\":\"OK\",\"keyIssue\":\"" + keyIssue + "\"}");
    }

    
    @RequestMapping("/show/{keyIssue}")
    public String show(@PathVariable("keyIssue") String keyIssue, Model model, Principal principal) {
        
        Issue issue = issueService.showIssue(keyIssue, principal.getName());
        Project project = projectService.findById(issue.getIdProject());
        List<Comment> comments = issueService.findAllComments(issue.getIdIssue());
        String prevKeyIssue = issueService.findPrevKeyIssue(issue.getCounter(), issue.getIdProject(), project.getKeyProject());
        String nextKeyIssue = issueService.findNextKeyIssue(issue.getCounter(), issue.getIdProject(), project.getKeyProject());
        Set<String> transitions = issueService.getTransitions(issue.getIdIssue(), principal.getName());
        List<IssueType> issueTypes = issueService.findAllTypes();
        List<IssuePriority> issuePriorities = issueService.findAllPriorities();
        List<OptionDto> assignees = projectService.listAssigneesOptions(issue.getIdIssue());
        for (String issueStatusEnum : transitions) {
            logger.info(issueStatusEnum);
        }
        model.addAttribute("issue", issue);
        model.addAttribute("project", project);
        model.addAttribute("comments", comments);
        model.addAttribute("prevKeyIssue", prevKeyIssue);
        model.addAttribute("nextKeyIssue", nextKeyIssue);
        model.addAttribute("transitions", transitions);
        model.addAttribute("creation", HumanizeTimeUtils.humanizedTime(issue.getCreation()));
        model.addAttribute("issueTypes", issueTypes);
        model.addAttribute("issuePriorities", issuePriorities);
        model.addAttribute("assignees", assignees);
        if (issue.getModification() != null) {
            model.addAttribute("modification", HumanizeTimeUtils.humanizedTime(issue.getModification()));
        } else {
            model.addAttribute("modification", "");
        }
        if (issue.getResolved() != null) {
            model.addAttribute("resolved", HumanizeTimeUtils.humanizedTime(issue.getResolved()));
        } else {
            model.addAttribute("resolved", "");
        }
        return "issues/show";
    }
  
    @RequestMapping("/addComment")
    public String addComment(Comment comment, Model model, Principal principal) {       
        issueService.addComment(comment, principal.getName());    
        comment.setCreationString(HumanizeTimeUtils.humanizedTime(comment.getCreation()));
        model.addAttribute("comment", comment);
        return "issues/comment :: comment";
    }
    
    @RequestMapping("/show/{idIssue}/history")
    public String history(@PathVariable("idIssue") Long idIssue, Model model) {
        logger.info(String.format("history from issue: %d", idIssue));
        List<IssueHistory> histories = issueService.findAllHistories(idIssue);
        logger.info(String.format("histories=%d", histories.size()));
        model.addAttribute("histories", histories);
        return "issues/histories :: histories";
    }
    
    @RequestMapping("/show/{idIssue}/comments")
    public String comments(@PathVariable("idIssue") Long idIssue, Model model) {
        List<Comment> comments =  issueService.findAllComments(idIssue);
        model.addAttribute("comments", comments);
        return "issues/comments :: comments";
    }
    
    @RequestMapping("/show/{idIssue}/activities")
    public String activities(@PathVariable("idIssue") Long idIssue, Model model) {
        List<Activity> activities =  activityService.findAllByIdIssue(idIssue);
        model.addAttribute("activities", activities);
        return "activities/list :: activities";
    }
    
    @RequestMapping("/show/{idIssue}/transitions")
    public String transitions(@PathVariable("idIssue") Long idIssue, Model model) {
        logger.info(String.format("transitions from issue: %d", idIssue));
        List<IssueTransition> transitions = issueService.findAllTransitions(idIssue);
        logger.info(String.format("transitions=%d", transitions.size()));
        model.addAttribute("transitions", transitions);
        return "issues/transitions :: transitions";
    }
    
    @RequestMapping("/{idIssue}/changeField")
    @ResponseBody
    public ResponseEntity<ResultDto> changeField(@PathVariable("idIssue") Long idIssue,
    		@RequestParam(name = "fieldName", required = true) String fieldName,
    		@RequestParam(name = "fieldValue", required = true) String fieldValue,
    		Principal principal) {
    	
    	logger.info(String.format("issues/%d/changeField", idIssue));
    	logger.info(String.format("fieldName = %s, fieldValue = %s", fieldName, fieldValue));
    	
    	ResultDto result =  issueService.updateField(idIssue, fieldName, fieldValue, principal.getName());
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }
}

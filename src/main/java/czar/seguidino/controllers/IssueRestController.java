package czar.seguidino.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import czar.seguidino.dtos.OptionDto;
import czar.seguidino.dtos.ResultDto;
import czar.seguidino.entities.IssuePriority;
import czar.seguidino.entities.IssueResolution;
import czar.seguidino.entities.IssueType;
import czar.seguidino.services.IssueService;

@RestController
@RequestMapping("/api/issues")
public class IssueRestController {
    
    private Logger logger = LoggerFactory.getLogger(IssueRestController.class);
    
    private static final Long DEFAULT_RESOLUTION_ID = 1L;
    
    @Autowired
    IssueService issueService;
    
    @RequestMapping("/types")
    public List<OptionDto> types() {
        List<IssueType> issueTypes = issueService.findAllTypes();
        return issueTypes
                .stream()
                .map(it -> new OptionDto(it.getIdIssueType(), it.getName(), it.getIcon(), false))
                .collect(Collectors.toList());
    }
    
    @RequestMapping("/priorities")
    public List<OptionDto> priorities() {
        List<IssuePriority> issuePriorities = issueService.findAllPriorities();
        return issuePriorities
                .stream()
                .map(ip -> new OptionDto(ip.getIdIssuePriority(), ip.getName(), ip.getIcon(), false))
                .collect(Collectors.toList());
    }
    
    @RequestMapping("/resolutions")
    public List<OptionDto> resolutions() {
        List<IssueResolution> issueResolutions = issueService.findAllResolutions();
        return issueResolutions
                .stream()
                .map(ip -> new OptionDto(ip.getIdIssueResolution(), ip.getName(), null, ip.getIdIssueResolution().equals(DEFAULT_RESOLUTION_ID)))
                .collect(Collectors.toList());
    }
    
    @RequestMapping("/{idIssue}/startProgress")
    public ResultDto startProgress(@PathVariable(value = "idIssue") Long idIssue, Principal principal) {
        logger.info(String.format("startProgress(%d)", idIssue));
        return issueService.startProgress(idIssue, principal.getName());
    }
    
    @RequestMapping("/{idIssue}/stopProgress")
    public ResultDto stopProgress(@PathVariable(value = "idIssue") Long idIssue, Principal principal) {
        logger.info(String.format("stopProgress(%d)", idIssue));
        return issueService.stopProgress(idIssue, principal.getName());
    }
}

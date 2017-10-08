package czar.seguidino.services.impl;

import static czar.seguidino.utils.IssueUtils.activity;
import static czar.seguidino.utils.IssueUtils.comment;
import static czar.seguidino.utils.IssueUtils.issueHistory;
import static czar.seguidino.utils.IssueUtils.issueHistoryDetail;
import static czar.seguidino.utils.IssueUtils.issueTransition;
import static czar.seguidino.utils.IssueUtils.FIELD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import czar.seguidino.dtos.MenuItem;
import czar.seguidino.dtos.ResultDto;
import czar.seguidino.entities.Activity;
import czar.seguidino.entities.Comment;
import czar.seguidino.entities.Filter;
import czar.seguidino.entities.FilterParam;
import czar.seguidino.entities.Issue;
import czar.seguidino.entities.IssueAccess;
import czar.seguidino.entities.IssueHistory;
import czar.seguidino.entities.IssueHistoryDetail;
import czar.seguidino.entities.IssuePriority;
import czar.seguidino.entities.IssueProgress;
import czar.seguidino.entities.IssueResolution;
import czar.seguidino.entities.IssueStatus;
import czar.seguidino.entities.IssueTransition;
import czar.seguidino.entities.IssueType;
import czar.seguidino.entities.Project;
import czar.seguidino.entities.ProjectComponent;
import czar.seguidino.entities.ProjectRole;
import czar.seguidino.entities.Transition;
import czar.seguidino.entities.User;
import czar.seguidino.enums.ActionEnum;
import czar.seguidino.enums.FieldEnum;
import czar.seguidino.enums.IssueStatusEnum;
import czar.seguidino.repositories.ActivityRepository;
import czar.seguidino.repositories.CommentRepository;
import czar.seguidino.repositories.FilterRepository;
import czar.seguidino.repositories.IssueAccessRepository;
import czar.seguidino.repositories.IssueCustomRepository;
import czar.seguidino.repositories.IssueHistoryRepository;
import czar.seguidino.repositories.IssuePriorityRepository;
import czar.seguidino.repositories.IssueProgressRepository;
import czar.seguidino.repositories.IssueRepository;
import czar.seguidino.repositories.IssueResolutionRepository;
import czar.seguidino.repositories.IssueStatusRepository;
import czar.seguidino.repositories.IssueTransitionRepository;
import czar.seguidino.repositories.IssueTypeRepository;
import czar.seguidino.repositories.ProjectComponentRepository;
import czar.seguidino.repositories.ProjectMemberRepository;
import czar.seguidino.repositories.ProjectRepository;
import czar.seguidino.repositories.TransitionRepository;
import czar.seguidino.repositories.UserRepository;
import czar.seguidino.services.IssueService;
import czar.seguidino.utils.DateTimeUtils;
import czar.seguidino.utils.HumanizeTimeUtils;

@Service("issueService")
public class IssueServiceImpl implements IssueService {
    
    private static final String KEY_DEF = "%s-%d";
    
    private static final int MAX_SIZE_MENU_ITEM = 96;
    
    private Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);
    
    @Autowired
    private IssueRepository issueRepository;
    
    @Autowired
    private IssueTypeRepository issueTypeRepository;

    @Autowired
    private IssuePriorityRepository issuePriorityRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private IssueCustomRepository issueCustomRepository;
    
    @Autowired
    private IssueAccessRepository issueAccessRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private IssueProgressRepository issueProgressRepository;
    
    @Autowired
    private IssueHistoryRepository issueHistoryRepository;
    
    @Autowired
    private IssueStatusRepository issueStatusRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private IssueResolutionRepository issueResolutionRepository;
    
    @Autowired
    private IssueTransitionRepository issueTransitionRepository;
    
    @Autowired
    private ProjectComponentRepository projectComponentRepository;
    
    @Autowired
    private ProjectMemberRepository projectMemberRepository;
    
    @Autowired
    private TransitionRepository transitionRepository;
    
    @Autowired
    private ActivityRepository activityRepository;
    
    @Autowired
    private FilterRepository filterRepository;
    
    @Override
    public Page<Issue> find(Issue issue) {
        Example<Issue> example = Example.of(issue);
        PageRequest pr = new PageRequest(0, 100);
        return issueRepository.findAll(example, pr);
    }

    @Override
    public List<IssueType> findAllTypes() {
        return issueTypeRepository.findAll();
    }
    
    @Override
    public List<IssuePriority> findAllPriorities() {
        return issuePriorityRepository.findAll();
    }

    @Override
    @Transactional
    public String save(Issue issue, List<Long> idsComponents) {
        
        Date now = new Date();
        
        Project project = projectRepository.findOne(issue.getIdProject());
        Long max = issueCustomRepository.getMaxCounterByIdProject(issue.getIdProject());
        max += 1L;
        String key = String.format("%s-%d", project.getKeyProject(), max);
        issue.setKeyIssue(key);
        issue.setCreation(now);
        IssueStatus status = new IssueStatus();
        status.setIdIssueStatus(IssueStatusEnum.OPEN.getId());
        issue.setIssueStatus(status);
        issue.setCounter(max);
        if (idsComponents != null && !idsComponents.isEmpty()) {
            Set<ProjectComponent> components = projectComponentRepository.findByidComponentIn(idsComponents);
            issue.setProjectComponents(components);
        }
        issueRepository.save(issue);
        
        IssueTransition issueTransition = issueTransition(issue.getIdIssue(), issue.getAuthor(), issue.getIssueStatus(), now);
        issueTransitionRepository.save(issueTransition);
        

        Activity activity = activity(ActionEnum.CREATE_ISSUE, now, issue, issue.getAuthor());
        activityRepository.save(activity);
        
        return key;
    }

    @Override
    public Issue showIssue(String keyIssue, String username) {
        Issue issue = issueRepository.findOneByKeyIssue(keyIssue);
    
        User user = userRepository.findByUsername(username);
        
        IssueAccess issueAccess = issueAccessRepository.findOneByIdUserAndIssue(user.getIdUser(), issue);
        
        if (issueAccess == null) {
            issueAccess = new IssueAccess();
            issueAccess.setIssue(issue);
            issueAccess.setIdUser(user.getIdUser());
        }
        issueAccess.setAccess(new Date());
        
        issueAccessRepository.save(issueAccess);
        
        return issue;
    }

    @Override
    @Transactional
    public ResultDto startProgress(Long idIssue, String username) {
        Issue issue = issueRepository.findOne(idIssue);
        User user = userRepository.findByUsername(username);
        
        Date now = new Date();
        IssueProgress progress = new IssueProgress();
        progress.setIdIssue(idIssue);
        progress.setIdUser(user.getIdUser());
        progress.setStart(now);
        
        IssueStatusEnum current = IssueStatusEnum.findById(issue.getIssueStatus().getIdIssueStatus());
        
        IssueHistory history = issueHistory(idIssue, user, ActionEnum.MADE_CHANGES, now);
        
        IssueHistoryDetail detail = issueHistoryDetail(history, FieldEnum.STATUS, current, IssueStatusEnum.IN_PROGRESS);
        history.setDetails(Arrays.asList(detail));
        
        IssueStatus status = issueStatusRepository.findOne(IssueStatusEnum.IN_PROGRESS.getId());
        issue.setIssueStatus(status);
        issue.setModification(now);
        
        IssueTransition issueTransition = issueTransition(issue.getIdIssue(), user, issue.getIssueStatus(), now); 
        
        issueRepository.save(issue);
        issueProgressRepository.save(progress);
        issueHistoryRepository.save(history);
        issueTransitionRepository.save(issueTransition);
    
        Activity activity = activity(ActionEnum.START_PROGRESS, now, issue, user);
        activityRepository.save(activity);
        
        ResultDto resultDto = new ResultDto();
        resultDto.setResult("OK");
        resultDto.setIssueStatus(status);
        return resultDto;
    }
    
    @Override
    @Transactional
    public ResultDto stopProgress(Long idIssue, String username) {
        Issue issue = issueRepository.findOne(idIssue);
        User user = userRepository.findByUsername(username);
        
        Date now = new Date();
        IssueProgress progress = issueProgressRepository.getOneRunningByIdIssue(idIssue);
        Date start = progress.getStart();
        long hours = DateTimeUtils.hoursBetween(start, now);
        progress.setStop(now);
        progress.setHours(hours);
        
        List<IssueTransition> lastTwo = issueTransitionRepository.findTop2ByIdIssueOrderByCreationDesc(idIssue);
        IssueStatusEnum lastBeforeInProgress = IssueStatusEnum.OPEN;
        if (lastTwo != null && lastTwo.size() == 2) {
            lastBeforeInProgress = IssueStatusEnum.findById(lastTwo.get(1).getIssueStatus().getIdIssueStatus());
        }
        
        IssueHistory history = issueHistory(idIssue, user, ActionEnum.MADE_CHANGES, now);
        
        IssueHistoryDetail detail = issueHistoryDetail(history, FieldEnum.STATUS, IssueStatusEnum.IN_PROGRESS, lastBeforeInProgress);
        history.setDetails(Arrays.asList(detail));
        
        IssueStatus status = issueStatusRepository.findOne(IssueStatusEnum.OPEN.getId());
        issue.setIssueStatus(status);
        issue.setModification(now);
        
        IssueTransition issueTransition = issueTransition(issue.getIdIssue(), user, issue.getIssueStatus(), now);
        
        issueRepository.save(issue);
        issueProgressRepository.save(progress);
        issueHistoryRepository.save(history);
        issueTransitionRepository.save(issueTransition);
        
        Activity activity = activity(ActionEnum.STOP_PROGRESS, now, issue, user);
        activityRepository.save(activity);
        
        ResultDto resultDto = new ResultDto();
        resultDto.setResult("OK");
        resultDto.setIssueStatus(status);
        return resultDto;
    }

    @Transactional
    @Override
    public void addComment(Comment comment, String username) {
        Date now = new Date();
        User user = userRepository.findByUsername(username);
        comment.setAuthor(user);
        comment.setCreation(now);
        
        Issue issue = issueRepository.findOne(comment.getIdIssue());
        issue.setModification(now);

        commentRepository.save(comment);
        issueRepository.save(issue);
        
        Activity activity = activity(ActionEnum.ADD_COMMENT, now, issue, user, comment.getText());
        activityRepository.save(activity);
    }

    @Override
    public List<Comment> findAllComments(Long idIssue) {
        List<Comment> comments = commentRepository.findAllByIdIssueOrderByCreation(idIssue);
        for (Comment c : comments) {
            c.setCreationString(HumanizeTimeUtils.humanizedTime(c.getCreation()));
        }
        return comments;
    }

    @Override
    public List<IssueResolution> findAllResolutions() {
        return issueResolutionRepository.findAll();
    }

    @Override
    @Transactional
    public String resolve(Issue issue, String textComment, String username) {
        Date now = new Date();
        
        Issue issuedb = issueRepository.findOne(issue.getIdIssue());
        User user = userRepository.findByUsername(username);
        
        IssueStatus status = issueStatusRepository.findOne(IssueStatusEnum.RESOLVED.getId());
        Long idIssueStatusPrev = issuedb.getIssueStatus().getIdIssueStatus();
        IssueStatusEnum prevStatus = IssueStatusEnum.findById(idIssueStatusPrev);
        
        IssueResolution resolution = issueResolutionRepository.findOne(issue.getIssueResolution().getIdIssueResolution());
        User prevUser = userRepository.findOne(issuedb.getAssignee().getIdUser());
        
        issuedb.setIssueStatus(status);        
        issuedb.setIssueResolution(issue.getIssueResolution());
        issuedb.setAssignee(issue.getAssignee());
        issuedb.setModification(now);
        issuedb.setResolved(now);
        issueRepository.save(issuedb);
        
        Comment comment = comment(user, now, issuedb.getIdIssue(), textComment);
        commentRepository.save(comment);
        
        IssueTransition issueTransition = issueTransition(issuedb.getIdIssue(), user, status, now);
        issueTransitionRepository.save(issueTransition);
        
        IssueProgress progress = issueProgressRepository.getOneRunningByIdIssue(issuedb.getIdIssue());
        if (progress != null) {
            Date start = progress.getStart();
            long hours = DateTimeUtils.hoursBetween(start, now);
            progress.setStop(now);
            progress.setHours(hours);
        } else {
            progress = new IssueProgress();
            progress.setIdIssue(issuedb.getIdIssue());
            progress.setIdUser(user.getIdUser());
            progress.setStart(now);
            progress.setStop(now);
            progress.setHours(0L);
        }
        issueProgressRepository.save(progress);
        
        IssueHistory history = issueHistory(issuedb.getIdIssue(), user, ActionEnum.MADE_CHANGES, now);
        
        List<IssueHistoryDetail> details = new ArrayList<>();
        
        IssueHistoryDetail statusChange = issueHistoryDetail(history, FieldEnum.STATUS, prevStatus, IssueStatusEnum.RESOLVED);
        details.add(statusChange);
        
        IssueHistoryDetail resolutionChange = issueHistoryDetail(history, FieldEnum.RESOLUTION, null, resolution);
        details.add(resolutionChange);
        
        if (prevUser.getIdUser().compareTo(issue.getAssignee().getIdUser()) != 0) {
            User currentUser = userRepository.findOne(issue.getAssignee().getIdUser());
            IssueHistoryDetail assigneeChange = issueHistoryDetail(history, FieldEnum.ASSIGNEE, prevUser, currentUser);
            details.add(assigneeChange);
        }
        
        history.setDetails(details);
        
        issueHistoryRepository.save(history);
        
        Activity activity = activity(ActionEnum.RESOLVE_ISSUE, now, issuedb, user);
        activityRepository.save(activity);
        
        return issuedb.getKeyIssue();
    }

    @Override
    @Transactional
    public String close(Issue issue, String textComment, String username) {
        Date now = new Date();
        
        Issue issuedb = issueRepository.findOne(issue.getIdIssue());
        User user = userRepository.findByUsername(username);
        
        IssueStatus status = issueStatusRepository.findOne(IssueStatusEnum.CLOSED.getId());
        Long idIssueStatusPrev = issuedb.getIssueStatus().getIdIssueStatus();
        IssueStatusEnum prevStatus = IssueStatusEnum.findById(idIssueStatusPrev);
        
        IssueResolution resolution = issueResolutionRepository.findOne(issue.getIssueResolution().getIdIssueResolution());
        IssueResolution prevResolution = null;
        if (issuedb.getIssueResolution() != null) {
            prevResolution = issueResolutionRepository.findOne(issuedb.getIssueResolution().getIdIssueResolution());
        }
        
        issuedb.setIssueStatus(status);        
        issuedb.setIssueResolution(issue.getIssueResolution());
        issuedb.setAssignee(issue.getAssignee());
        issuedb.setModification(now);
        if (issuedb.getResolved() == null) {
            issuedb.setResolved(now);
        }
        issueRepository.save(issuedb);
        
        Comment comment = comment(user, now, issuedb.getIdIssue(), textComment);
        commentRepository.save(comment);
        
        IssueTransition issueTransition = issueTransition(issuedb.getIdIssue(), user, status, now);
        issueTransitionRepository.save(issueTransition);
        
        IssueHistory history = issueHistory(issuedb.getIdIssue(), user, ActionEnum.MADE_CHANGES, now);
        
        List<IssueHistoryDetail> details = new ArrayList<>();
        
        IssueHistoryDetail changeStatus = issueHistoryDetail(history, FieldEnum.STATUS, prevStatus, IssueStatusEnum.CLOSED);
        details.add(changeStatus);
        
        if (prevResolution == null) {
            IssueHistoryDetail changeResolution = issueHistoryDetail(history, FieldEnum.RESOLUTION, null, resolution);
            details.add(changeResolution);
        } else if (prevResolution.getIdIssueResolution().compareTo(issue.getIssueResolution().getIdIssueResolution()) != 0) {
            IssueHistoryDetail changeResolution = issueHistoryDetail(history, FieldEnum.RESOLUTION, prevResolution, resolution);
            details.add(changeResolution);
        }
        
        if (issuedb.getAssignee().getIdUser().compareTo(issue.getAssignee().getIdUser()) != 0) {
            User current = userRepository.findOne(issue.getAssignee().getIdUser());
            IssueHistoryDetail ihd = issueHistoryDetail(history, FieldEnum.ASSIGNEE, issuedb.getAssignee(), current);
            details.add(ihd);
        }
        
        history.setDetails(details);        
        issueHistoryRepository.save(history);
        
        Activity activity = activity(ActionEnum.CLOSE_ISSUE, now, issuedb, user);
        activityRepository.save(activity);
                
        return issuedb.getKeyIssue();
    }
    
    @Override
    @Transactional
    public String reopen(Issue issue, String textComment, String username) {
        Date now = new Date();
        
        Issue issuedb = issueRepository.findOne(issue.getIdIssue());
        User user = userRepository.findByUsername(username);
        
        IssueStatus status = issueStatusRepository.findOne(IssueStatusEnum.REOPEN.getId());
        Long idIssueStatusPrev = issuedb.getIssueStatus().getIdIssueStatus();
        IssueStatusEnum prevStatus = IssueStatusEnum.findById(idIssueStatusPrev);
        
        IssueResolution prevResolution = issueResolutionRepository.findOne(issuedb.getIssueResolution().getIdIssueResolution());
        
        issuedb.setIssueStatus(status);        
        issuedb.setIssueResolution(null);
        issuedb.setAssignee(issue.getAssignee());
        issuedb.setModification(now);
        issuedb.setResolved(null);
        issueRepository.save(issuedb);
        
        Comment comment = comment(user, now, issuedb.getIdIssue(), textComment);
        commentRepository.save(comment);
        
        IssueTransition issueTransition = issueTransition(issuedb.getIdIssue(), user, status, now);
        issueTransitionRepository.save(issueTransition);
        
        IssueHistory history = issueHistory(issuedb.getIdIssue(), user, ActionEnum.MADE_CHANGES, now);
        
        List<IssueHistoryDetail> details = new ArrayList<>();
        
        IssueHistoryDetail changeStatus = issueHistoryDetail(history, FieldEnum.STATUS, prevStatus, IssueStatusEnum.REOPEN);
        details.add(changeStatus);
        
        IssueHistoryDetail changeResolution = issueHistoryDetail(history, FieldEnum.RESOLUTION, prevResolution, null);
        details.add(changeResolution);
        
        if (issuedb.getAssignee().getIdUser().compareTo(issue.getAssignee().getIdUser()) != 0) {
            User current = userRepository.findOne(issue.getAssignee().getIdUser());
            IssueHistoryDetail ihd = issueHistoryDetail(history, FieldEnum.ASSIGNEE, issuedb.getAssignee(), current);
            details.add(ihd);
        }
        
        history.setDetails(details);        
        issueHistoryRepository.save(history);
        
        Activity activity = activity(ActionEnum.REOPEN_ISSUE, now, issuedb, user);
        activityRepository.save(activity);
                
        return issuedb.getKeyIssue();
    }


    @Override
    public List<IssueHistory> findAllHistories(Long idIssue) {
        List<IssueHistory> histories = issueHistoryRepository.findAllByIdIssueOrderByCreationAsc(idIssue);
        for (IssueHistory h : histories) {
            h.setCreationString(HumanizeTimeUtils.humanizedTime(h.getCreation()));
        }
        return histories;
    }

    @Override
    public String findPrevKeyIssue(Long counter, Long idProject, String keyProject) {
        if (counter > 1) {
            return String.format(KEY_DEF, keyProject, counter - 1);
        } else {
            Long max = issueCustomRepository.getMaxCounterByIdProject(idProject);
            return String.format(KEY_DEF, keyProject, max);
        }
    }

    @Override
    public String findNextKeyIssue(Long counter, Long idProject, String keyProject) {
        Long max = issueCustomRepository.getMaxCounterByIdProject(idProject);
        if (counter.compareTo(max) < 0) {
            return String.format(KEY_DEF, keyProject, counter + 1);
        } else {
            return String.format("%s-1", keyProject);
        }
    }

    @Override
    public Set<String> getTransitions(Long idIssue, String username) {
        
        Issue issue = issueRepository.findOne(idIssue);
        User user = userRepository.findByUsername(username);
        
        List<ProjectRole> roles = projectMemberRepository.findByIdProjectAndUser(issue.getIdProject(), user.getIdUser());
        List<Long> idsRoles = roles.stream().map(r -> r.getIdRole()).collect(Collectors.toList());
        List<Transition> transitions = transitionRepository.findAllByIdIssueStatusFromAndIdRolIn(issue.getIssueStatus().getIdIssueStatus(), idsRoles);
        Set<String> status = transitions.stream().map(t -> IssueStatusEnum.findById(t.getIdIssueStatusTo()).toString()).collect(Collectors.toSet());
        return status;
    }

    @Override
    public List<IssueTransition> findAllTransitions(Long idIssue) {
        List<IssueTransition> transitions = issueTransitionRepository.findAllByIdIssueOrderByCreation(idIssue);
        for (IssueTransition it : transitions) {
            it.setCreationString(HumanizeTimeUtils.humanizedTime(it.getCreation()));
        }
        return transitions;
    }

    @Override
    public Page<Issue> findByIdProjectAndFilter(Long idProject, String filterName, Pageable pageable, String username) {
        Filter filter = filterRepository.findOneByIdProjectAndName(idProject, filterName);
        Map<String, Object> params = new HashMap<>();
        for (FilterParam filterParam : filter.getParams()) {
            String key = filterParam.getName();
            Object value = null;
            if ("java.lang.Long".equals(filterParam.getParamType())) {
                value = Long.valueOf(filterParam.getValue());
            } else if ("java.security.Principal".equals(filterParam.getParamType())) {
                User user = userRepository.findByUsername(username);
                value = user.getIdUser();
            }
            params.put(key, value);
        }
        return issueCustomRepository.findByQueryAndParams(filter.getQuery(), params, pageable);
    }

	@Override
	public List<MenuItem> buildIssueMenuItems(String username) {
		List<MenuItem> items = new ArrayList<>();
		User user = userRepository.findByUsername(username);
		
		items.add(new MenuItem("issues/browse", "Search for Issues", false, false));
		items.add(new MenuItem("javascript:SEGUIDINO.showIssueForm();", "Create Issue", false, false));
		items.add(MenuItem.SEPARATOR_MENU_ITEM);
		
		
		PageRequest pageable = new PageRequest(0, 5, Sort.Direction.DESC, "access");
        Page<Issue> page = issueAccessRepository.findIssuesByIdUser(user.getIdUser(), pageable);
        
        if (page.getContent().size() > 1) {
            List<Issue> last = page.getContent();
            items.add(new MenuItem(null, "Recent Issues", true, false));
            for (Issue issue : last) {
                items.add(issueToMenuItem(issue));
            }
            items.add(MenuItem.SEPARATOR_MENU_ITEM);
        }
        
        items.add(new MenuItem(null, "Filters", true, false));
        items.add(new MenuItem("issues/browse?f=MyOpenIssues", "My Open Issues", null, false, false));
        items.add(new MenuItem("issues/browse?f=ReportedByMe", "Reported by Me", null, false, false));
        items.add(MenuItem.SEPARATOR_MENU_ITEM);
        items.add(new MenuItem("filters/manage", "Manage Filters", null, false, false));
        
		return items;
	}
	
	private MenuItem issueToMenuItem(Issue issue) {
        String url = String.format("/issues/show/%s", issue.getKeyIssue());
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s", issue.getKeyIssue(), issue.getSubject()));
        if (sb.length() > MAX_SIZE_MENU_ITEM) {
        	sb = new StringBuilder(sb.substring(0, MAX_SIZE_MENU_ITEM));
        	sb.append(" ...");
        }
        String name = sb.toString();
        String avatar = issue.getIssueType().getIcon();
        return new MenuItem(url, name, avatar, false, false);
    }

	@Override
	public ResultDto updateField(Long idIssue, String fieldName, String fieldValue, String username) {
		ResultDto result = new ResultDto();
        logger.info(String.format("updateField(%d, %s, %s, %s)", idIssue, fieldName, fieldValue, username));        
        User user = userRepository.findByUsername(username);        
        Issue issue = issueRepository.findOne(idIssue);        	
    	FieldEnum field = FieldEnum.findByDescription(fieldName);
    	String oldValue = null;
    	String newValue = null;  
    	ActionEnum action = null;
    	String actionParam = null;
    	switch(field) {
    		case SUBJECT:
    			oldValue = issue.getSubject();
    			newValue = new String(fieldValue);
    			issue.setSubject(newValue);
    			action = ActionEnum.CHANGE_SUBJECT;
    			actionParam = newValue;
    			break;
    		case TYPE:
    			oldValue = String.format(FIELD, issue.getIssueType().getName(), issue.getIssueType().getIdIssueType());
    			IssueType type = issueTypeRepository.findOne(Long.valueOf(fieldValue));
    			newValue = String.format(FIELD, type.getName(), type.getIdIssueType());
    			issue.setIssueType(type);
    			action = ActionEnum.CHANGE_TYPE;
    			actionParam = type.getName();
    			break;
    		case PRIORITY:
    			oldValue = String.format(FIELD, issue.getIssuePriority().getName(), issue.getIssuePriority().getIdIssuePriority());
    			IssuePriority priority = issuePriorityRepository.findOne(Long.valueOf(fieldValue));
    			newValue = String.format(FIELD, priority.getName(), priority.getIdIssuePriority());
    			action = ActionEnum.CHANGE_PRIORITY;
    			actionParam = priority.getName();
    			break;
    		case ASSIGNEE:
    		case RESOLUTION:
    		case STATUS:
    		default:
    			break;
    	}
		
		Date now = new Date();
		issueRepository.save(issue);			
		IssueHistory history = issueHistory(issue.getIdIssue(), user, ActionEnum.MADE_CHANGES, now);	        
        List<IssueHistoryDetail> details = new ArrayList<>();	        
        IssueHistoryDetail changeSubject = issueHistoryDetail(history, field, oldValue, newValue);
        details.add(changeSubject);	        
        history.setDetails(details);        
        issueHistoryRepository.save(history);        
        Activity activity = activity(action, now, issue, user, null, actionParam);
        activityRepository.save(activity);
		
		result.setResult("OK");
		
        return result;
	}
}

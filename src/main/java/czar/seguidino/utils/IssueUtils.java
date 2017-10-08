package czar.seguidino.utils;

import java.util.Date;

import czar.seguidino.entities.Activity;
import czar.seguidino.entities.Comment;
import czar.seguidino.entities.Issue;
import czar.seguidino.entities.IssueHistory;
import czar.seguidino.entities.IssueHistoryDetail;
import czar.seguidino.entities.IssueResolution;
import czar.seguidino.entities.IssueStatus;
import czar.seguidino.entities.IssueTransition;
import czar.seguidino.entities.User;
import czar.seguidino.enums.ActionEnum;
import czar.seguidino.enums.FieldEnum;
import czar.seguidino.enums.IssueStatusEnum;

public final class IssueUtils {
    
    public static final String FIELD = "%s[%d]";
    
    public static final String EMPTY = "";
    
    private IssueUtils() {
        
    }
    
    public static IssueTransition issueTransition(final Long idIssue, final User user, final IssueStatus status, final Date date) {
        IssueTransition issueTransition = new IssueTransition();
        issueTransition.setIdIssue(idIssue);
        issueTransition.setUser(user);
        issueTransition.setIssueStatus(status);
        issueTransition.setCreation(date);
        return issueTransition;
    };
    
    
    public static Activity activity(final ActionEnum action, final Date date, final Issue issue, final User user, final String detail, final String param) {
        Activity activity = new Activity();
        if (param != null) {
        	activity.setAction(action.toString());
        } else {
        	activity.setAction(action.toString(param));
        }
        activity.setCreation(date);
        activity.setIdProject(issue.getIdProject());
        activity.setIssue(issue);
        activity.setUser(user);
        activity.setDetail(detail);
        return activity;
    }
    
    public static Activity activity(final ActionEnum action, final Date date, final Issue issue, final User user, final String detail) {
        return activity(action, date, issue, user, detail, null);
    }
    
    public static Activity activity(final ActionEnum action, final Date date, final Issue issue, final User user) {
        return activity(action, date, issue, user, null, null);
    }
    
    public static Comment comment(final User user, final Date date, final Long idIssue, final String text) {
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setCreation(date);
        comment.setIdIssue(idIssue);
        comment.setText(text);
        return comment;
    }
    
    public static IssueHistory issueHistory(final Long idIssue, final User user, final ActionEnum action, final Date date) {
        IssueHistory history = new IssueHistory();
        history.setIdIssue(idIssue);
        history.setUser(user);
        history.setAction(action.toString());
        history.setCreation(date);
        return history;
    }
    
    public static IssueHistoryDetail issueHistoryDetail(final IssueHistory history, final FieldEnum field, final IssueStatusEnum beforeValue, final IssueStatusEnum afterValue) {
        return issueHistoryDetail(history, field, field(beforeValue), field(afterValue));
    }
    
    public static IssueHistoryDetail issueHistoryDetail(final IssueHistory history, final FieldEnum field, final User beforeValue, final User afterValue) {
       return  issueHistoryDetail(history, field, field(beforeValue), field(afterValue));
    }
    
    public static IssueHistoryDetail issueHistoryDetail(final IssueHistory history, final FieldEnum field, final IssueResolution beforeRes, final IssueResolution resolution) {
        String before = EMPTY;
        String after = EMPTY;
        
        if (beforeRes != null) {
            before = field(beforeRes);
        }
        
        if (resolution != null) {
            after = field(resolution);
        }
        return issueHistoryDetail(history, field, before, after);
    }
    
    public static IssueHistoryDetail issueHistoryDetail(final IssueHistory history, final FieldEnum field, final String beforeValue, final String afterValue) {
        IssueHistoryDetail detail = new IssueHistoryDetail();
        detail.setIssueHistory(history);
        detail.setField(field.toString());
        detail.setBeforeValue(beforeValue);
        detail.setAfterValue(afterValue);
        return detail;
    }
    
    public static String field(final IssueStatusEnum issueStatusEnum) {
        return String.format(FIELD, issueStatusEnum.toString(), issueStatusEnum.getId());
    }
    
    public static String field(final IssueResolution issueResolution) {
        return String.format(FIELD, issueResolution.getName(), issueResolution.getIdIssueResolution());
    }
    
    public static String field(final User user) {
        return String.format(FIELD, user.getName(), user.getIdUser());
    }
}

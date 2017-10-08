package czar.seguidino.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import czar.seguidino.dtos.MenuItem;
import czar.seguidino.dtos.ResultDto;
import czar.seguidino.entities.Comment;
import czar.seguidino.entities.Issue;
import czar.seguidino.entities.IssueHistory;
import czar.seguidino.entities.IssuePriority;
import czar.seguidino.entities.IssueResolution;
import czar.seguidino.entities.IssueTransition;
import czar.seguidino.entities.IssueType;

public interface IssueService {
    Page<Issue> find(Issue issue);
    List<IssueType> findAllTypes();
    List<IssuePriority> findAllPriorities();
    List<IssueResolution> findAllResolutions();
    String save(Issue issue, List<Long> components);
    String resolve(Issue issue, String textComment, String username);
    String close(Issue issue, String textComment, String username);
    String reopen(Issue issue, String textComment, String username);
    Issue showIssue(String keyIssue, String username);
    ResultDto startProgress(Long idIssue, String username);
    ResultDto stopProgress(Long idIssue, String username);
    void addComment(Comment comment, String username);
    List<Comment> findAllComments(Long idIssue);
    List<IssueHistory> findAllHistories(Long idIssue);
    String findPrevKeyIssue(Long counter, Long idProject, String keyProject);
    String findNextKeyIssue(Long counter, Long idProject, String keyProject);
    Set<String> getTransitions(Long idIssue, String username);
    List<IssueTransition> findAllTransitions(Long idIssue);
    Page<Issue> findByIdProjectAndFilter(Long idProject, String filter, Pageable pageable, String username);
    List<MenuItem> buildIssueMenuItems(String username);
    ResultDto updateField(Long idIssue, String fieldName, String fieldValue, String username);
}

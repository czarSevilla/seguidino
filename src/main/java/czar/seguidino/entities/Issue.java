package czar.seguidino.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sg_issues")
public class Issue implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssue;
    private String keyIssue;
    private Long idProject;
    private User author;
    private String subject;
    private IssueType issueType;
    private IssuePriority issuePriority;
    private IssueResolution issueResolution;
    private IssueStatus issueStatus;
    private ProjectComponent projectComponent;
    private String description;
    private Date creation;
    private Date modification;
    private User assignee;
    private Long counter;
    private Date resolved;
    private Set<ProjectComponent> projectComponents;
    
    public Issue() {
        
    }

    @Id
    @Column(name = "id_issue")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(Long idIssue) {
        this.idIssue = idIssue;
    }

    @Column(name = "key_issue")
    public String getKeyIssue() {
        return keyIssue;
    }

    public void setKeyIssue(String keyIssue) {
        this.keyIssue = keyIssue;
    }

    @Column(name = "id_project")
    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    @Column
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to")
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_issue_type")
    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_issue_priority")
    public IssuePriority getIssuePriority() {
        return issuePriority;
    }

    public void setIssuePriority(IssuePriority issuePriority) {
        this.issuePriority = issuePriority;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_issue_resolution")
    public IssueResolution getIssueResolution() {
        return issueResolution;
    }

    public void setIssueResolution(IssueResolution issueResolution) {
        this.issueResolution = issueResolution;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_issue_status")
    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_component")
    public ProjectComponent getProjectComponent() {
        return projectComponent;
    }

    public void setProjectComponent(ProjectComponent projectComponent) {
        this.projectComponent = projectComponent;
    }

    @Column
    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "sg_issue_comp",
            joinColumns = @JoinColumn(name = "id_issue"),
            inverseJoinColumns = @JoinColumn(name = "id_component"))
    public Set<ProjectComponent> getProjectComponents() {
        return projectComponents;
    }

    public void setProjectComponents(Set<ProjectComponent> projectComponents) {
        this.projectComponents = projectComponents;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }
}

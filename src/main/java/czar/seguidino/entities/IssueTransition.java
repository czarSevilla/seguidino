package czar.seguidino.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "sg_issue_transitions")
public class IssueTransition implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueTransition;
    private Long idIssue;
    private User user;
    private IssueStatus issueStatus;
    private Date creation;
    private String creationString;
    
    public IssueTransition() {
        
    }

    @Id
    @Column(name = "id_issue_transition")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueTransition() {
        return idIssueTransition;
    }

    public void setIdIssueTransition(Long idIssueTransition) {
        this.idIssueTransition = idIssueTransition;
    }

    @Column(name = "id_issue", nullable = false)
    public Long getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(Long idIssue) {
        this.idIssue = idIssue;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_issue_status", nullable = false)
    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    @Transient
    public String getCreationString() {
        return creationString;
    }

    public void setCreationString(String creationString) {
        this.creationString = creationString;
    }
}

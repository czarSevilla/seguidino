package czar.seguidino.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "sg_issue_history")
public class IssueHistory implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueHistory;
    private Long idIssue;
    private User user;
    private String action;
    private Date creation;
    private List<IssueHistoryDetail> details;
    private String creationString;
    
    public IssueHistory() {
        
    }

    @Id
    @Column(name = "id_issue_history")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueHistory() {
        return idIssueHistory;
    }

    public void setIdIssueHistory(Long idIssueHistory) {
        this.idIssueHistory = idIssueHistory;
    }

    @Column(name = "id_issue", nullable = false)
    public Long getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(Long idIssue) {
        this.idIssue = idIssue;
    }

    @Column(nullable = false)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    @OneToMany(mappedBy = "issueHistory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<IssueHistoryDetail> getDetails() {
        return details;
    }

    public void setDetails(List<IssueHistoryDetail> details) {
        this.details = details;
    }

    @Transient
    public String getCreationString() {
        return creationString;
    }

    public void setCreationString(String creationString) {
        this.creationString = creationString;
    }
    
}

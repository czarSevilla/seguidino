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
@Table(name = "sg_activity")
public class Activity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idActivity;
    private Long idProject;
    private Issue issue;
    private User user ;
    private String action;
    private String detail;
    private Date creation;
    private String creationString;
    
    public Activity() {
        
    }

    @Id
    @Column(name = "id_activity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Long idActivity) {
        this.idActivity = idActivity;
    }

    @Column(name = "id_project", nullable = false)
    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    @Column(nullable = false)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_issue", nullable = false)
    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public String getCreationString() {
        return creationString;
    }

    public void setCreationString(String creationString) {
        this.creationString = creationString;
    }
}

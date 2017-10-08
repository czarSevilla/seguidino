package czar.seguidino.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sg_issue_progress")
public class IssueProgress implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueProgress;
    private Long idUser;
    private Long idIssue;
    private Date start;
    private Date stop;
    private Long hours;
    
    public IssueProgress() {
        
    }

    @Id
    @Column(name = "id_issue_progress")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueProgress() {
        return idIssueProgress;
    }

    public void setIdIssueProgress(Long idIssueProgress) {
        this.idIssueProgress = idIssueProgress;
    }

    @Column(name = "id_user", nullable = false)
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    @Column
    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }
}

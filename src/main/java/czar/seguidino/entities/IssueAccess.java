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

@Entity
@Table(name = "sg_issue_access")
public class IssueAccess implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueAccess;
    private Long idUser;
    private Issue issue;
    private Date access;
    
    public IssueAccess() {
        
    }

    @Id
    @Column(name = "id_issue_access")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueAccess() {
        return idIssueAccess;
    }

    public void setIdIssueAccess(Long idIssueAccess) {
        this.idIssueAccess = idIssueAccess;
    }

    @Column(name = "id_user", nullable = false)
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAccess() {
        return access;
    }

    public void setAccess(Date access) {
        this.access = access;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_issue")
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
}

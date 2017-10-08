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
@Table(name = "sg_project_access")
public class ProjectAccess implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idProjectAccess;
    private Long idUser;
    private Project project;
    private Date access;
    
    public ProjectAccess() {
        
    }

    @Id
    @Column(name = "id_project_access")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdProjectAccess() {
        return idProjectAccess;
    }

    public void setIdProjectAccess(Long idProjectAccess) {
        this.idProjectAccess = idProjectAccess;
    }

    @Column(name = "id_user", nullable = false)
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Column(name = "access", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAccess() {
        return access;
    }

    public void setAccess(Date access) {
        this.access = access;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    
}

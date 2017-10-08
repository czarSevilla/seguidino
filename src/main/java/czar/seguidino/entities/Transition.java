package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_transitions")
public class Transition implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idTransition;
    private Long idIssueStatusFrom;
    private Long idIssueStatusTo;
    private Long idRol;
    
    public Transition() {
        
    }

    @Id
    @Column(name = "id_transition")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdTransition() {
        return idTransition;
    }

    public void setIdTransition(Long idTransition) {
        this.idTransition = idTransition;
    }

    @Column(name = "id_issue_status_from", nullable = false)
    public Long getIdIssueStatusFrom() {
        return idIssueStatusFrom;
    }

    public void setIdIssueStatusFrom(Long idIssueStatusFrom) {
        this.idIssueStatusFrom = idIssueStatusFrom;
    }

    @Column(name = "id_issue_status_to", nullable = false)
    public Long getIdIssueStatusTo() {
        return idIssueStatusTo;
    }

    public void setIdIssueStatusTo(Long idIssueStatusTo) {
        this.idIssueStatusTo = idIssueStatusTo;
    }

    @Column(name = "id_rol", nullable = false)
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
    
    
}

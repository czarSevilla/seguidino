package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_issue_resolution")
public class IssueResolution implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueResolution;
    private String name;
    
    public IssueResolution() {
        
    }

    @Id
    @Column(name = "id_issue_resolution")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueResolution() {
        return idIssueResolution;
    }

    public void setIdIssueResolution(Long idIssueResolution) {
        this.idIssueResolution = idIssueResolution;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

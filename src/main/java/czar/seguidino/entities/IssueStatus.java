package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_issue_status")
public class IssueStatus implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueStatus;
    private String name;
    private String icon;
    
    public IssueStatus() {
        
    }

    @Id
    @Column(name = "id_issue_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueStatus() {
        return idIssueStatus;
    }

    public void setIdIssueStatus(Long idIssueStatus) {
        this.idIssueStatus = idIssueStatus;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    

}

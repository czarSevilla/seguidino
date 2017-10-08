package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_issue_types")
public class IssueType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueType;
    private String name;
    private String icon;
    
    public IssueType() {
        
    }

    @Id
    @Column(name = "id_issue_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueType() {
        return idIssueType;
    }

    public void setIdIssueType(Long idIssueType) {
        this.idIssueType = idIssueType;
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

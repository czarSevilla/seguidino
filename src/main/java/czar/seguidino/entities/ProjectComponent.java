package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sg_components")
public class ProjectComponent implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idComponent;
    private Long idProject;
    private String name;
    private User lead;
    private String description;
    
    public ProjectComponent() {
        
    }

    @Id
    @Column(name = "id_component")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(Long idComponent) {
        this.idComponent = idComponent;
    }

    @Column(name = "id_project", nullable = false)
    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    public User getLead() {
        return lead;
    }

    public void setLead(User lead) {
        this.lead = lead;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

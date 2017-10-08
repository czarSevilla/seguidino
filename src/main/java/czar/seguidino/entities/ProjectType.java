package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_project_types")
public class ProjectType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idProjectType;
    private String description;
    
    public ProjectType() {
        
    }

    @Id
    @Column(name = "id_project_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdProjectType() {
        return idProjectType;
    }

    public void setIdProjectType(Long idProjectType) {
        this.idProjectType = idProjectType;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}

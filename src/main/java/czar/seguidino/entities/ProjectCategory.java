package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_project_categories")
public class ProjectCategory implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idProjectCategory;
    private String name;
    
    public ProjectCategory() {
        
    }

    @Id
    @Column(name = "id_project_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdProjectCategory() {
        return idProjectCategory;
    }

    public void setIdProjectCategory(Long idProjectCategory) {
        this.idProjectCategory = idProjectCategory;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

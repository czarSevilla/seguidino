package czar.seguidino.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sg_filters")
public class Filter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idFilter;
    private Long idUser;
    private Long idProject;
    private String name;
    private String query;
    private List<FilterParam> params;
    
    public Filter() {
        
    }

    @Id
    @Column(name = "id_filter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdFilter() {
        return idFilter;
    }

    public void setIdFilter(Long idFilter) {
        this.idFilter = idFilter;
    }

    @Column(name = "id_user")
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_project")
    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "query", nullable = false)
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
    public List<FilterParam> getParams() {
        return params;
    }

    public void setParams(List<FilterParam> params) {
        this.params = params;
    }
    
    
}

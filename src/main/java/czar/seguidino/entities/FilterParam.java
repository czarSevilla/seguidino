package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sg_filter_params")
public class FilterParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idFilterParam;
    private Filter filter;
    private String name;
    private String value;
    private String paramType;
    
    public FilterParam() {
        
    }

    @Id
    @Column(name = "id_filter_param")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdFilterParam() {
        return idFilterParam;
    }

    public void setIdFilterParam(Long idFilterParam) {
        this.idFilterParam = idFilterParam;
    }

    @ManyToOne
    @JoinColumn(name = "id_filter", nullable = false)
    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value", nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "param_type", nullable = false)
    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}

package czar.seguidino.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sg_parameters")
public class Parameter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idParameter;
    private String key;
    private String value;
    
    public Parameter() {
        
    }

    @Id
    @Column(name = "id_parameter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(Long idParameter) {
        this.idParameter = idParameter;
    }

    @Column(name = "param_key", nullable = false)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "param_value", nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


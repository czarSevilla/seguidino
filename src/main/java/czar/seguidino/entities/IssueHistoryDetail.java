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
@Table(name = "sg_issue_history_det")
public class IssueHistoryDetail implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idIssueHistoryDetail;
    private IssueHistory issueHistory;
    private String field;
    private String beforeValue;
    private String afterValue;
    
    public IssueHistoryDetail() {
        
    }

    @Id
    @Column(name = "id_issue_history_det")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdIssueHistoryDetail() {
        return idIssueHistoryDetail;
    }

    public void setIdIssueHistoryDetail(Long idIssueHistoryDetail) {
        this.idIssueHistoryDetail = idIssueHistoryDetail;
    }

    @ManyToOne
    @JoinColumn(name = "id_issue_history", nullable = false)
    public IssueHistory getIssueHistory() {
        return issueHistory;
    }

    public void setIssueHistory(IssueHistory issueHistory) {
        this.issueHistory = issueHistory;
    }

    @Column(nullable = false)
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Column(name = "before_value", nullable = false)
    public String getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
    }

    @Column(name = "after_value", nullable = false)
    public String getAfterValue() {
        return afterValue;
    }

    public void setAfterValue(String afterValue) {
        this.afterValue = afterValue;
    }
}

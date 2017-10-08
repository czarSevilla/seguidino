package czar.seguidino.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "sg_comments")
public class Comment implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idComment;
    private Long idIssue;
    private User author;
    private String text;
    private Date creation;
    private Date modification;
    private String creationString;
    
    public Comment() {
        
    }

    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    @Column(name = "id_issue", nullable = false)
    public Long getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(Long idIssue) {
        this.idIssue = idIssue;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author", nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "comment", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }

    @Transient
    public String getCreationString() {
        return creationString;
    }

    public void setCreationString(String creationString) {
        this.creationString = creationString;
    }
}

package czar.seguidino.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sg_files_references")
public class FileReference implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idFileReference;
    private String fileName;
    private String originalName;
    private String contentType;
    private Long size;
    private String relativePath;
    private Date creation;
    
    public FileReference() {
        
    }

    @Id
    @Column(name = "id_file_reference")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdFileReference() {
        return idFileReference;
    }

    public void setIdFileReference(Long idFileReference) {
        this.idFileReference = idFileReference;
    }

    @Column(name = "file_name", nullable = true)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Column(name = "content_type", nullable = false)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Column(name="file_size", nullable = false)
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Column(name = "relative_path")
    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Column(name = "original_name", nullable = false)
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}


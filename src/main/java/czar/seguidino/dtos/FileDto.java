package czar.seguidino.dtos;

import org.springframework.http.MediaType;

public class FileDto {
    private MediaType contentType;
    private byte[] bytes;
    
    public FileDto() {
        
    }

    public MediaType getContentType() {
        return contentType;
    }

    public void setContentType(MediaType contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

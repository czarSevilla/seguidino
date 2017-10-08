package czar.seguidino.enums;

public enum IssueStatusEnum {
    OPEN(1L),
    IN_PROGRESS(2L),
    CLOSED(3L),
    REOPEN(4L),
    RESOLVED(5L);    
    
    private Long id;
    
    private IssueStatusEnum(Long pId) {
        this.id = pId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public static IssueStatusEnum findById(Long id) {
        for (IssueStatusEnum status : values()) {
            if (status.getId().compareTo(id) == 0) {
                return status;
            }
        }
        return null;
    }
}

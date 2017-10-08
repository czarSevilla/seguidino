package czar.seguidino.enums;

public enum IssueRoleEnum {
    ADMINISTRATOR(1L),
    DEVELOPER(2L);
    
    private Long id;
    
    private IssueRoleEnum(Long pId) {
        this.id = pId;
    }
    
    public Long getId() {
        return this.id;
    }
}

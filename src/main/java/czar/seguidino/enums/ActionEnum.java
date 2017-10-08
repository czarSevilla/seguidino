package czar.seguidino.enums;

public enum ActionEnum {
    CREATE_ISSUE(" create "),    
    START_PROGRESS(" start progress on "),    
    STOP_PROGRESS(" stop progress on "),    
    ADD_COMMENT(" added a comment on "),    
    RESOLVE_ISSUE(" resolve "),    
    CLOSE_ISSUE(" close "),    
    REOPEN_ISSUE(" reopen "),    
    MADE_CHANGES("made changes"),
    CHANGE_SUBJECT(" change subject "),
    CHANGE_TYPE(" change type "),
    CHANGE_PRIORITY(" change priority ");
    
    private String description;
    
    private ActionEnum(String desc) {
        this.description = desc;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
    
    public String toString(String param) {
    	return String.format("%s to \"%s\"", this.description, param);
    }
}

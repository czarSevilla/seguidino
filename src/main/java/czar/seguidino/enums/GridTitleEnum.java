package czar.seguidino.enums;

public enum GridTitleEnum {
    TITLE_SUMMARY("Status Summary"),
    STATUS("Status"),
    ISSUES("Issues"),
    PERCENTAGE("Percentage"),
    TITLE_UNRESOLVED_PRIORITY("Unresolved: By Priority"),
    PRIORITY("Priority"),
    TITLE_UNRESOLVED_ASSIGNEE("Unresolved: By Assignee"),
    ASSIGNEE("Assignee"),
    TITLE_UNRESOLVED_TYPE("Unresolved: By Issue Type"),
    TYPE("Issue Type"),
    TITLE_COMPONENT("Unresolved: By Component"),
    COMPONENT("Component");
    
    private String description;
    
    private GridTitleEnum(String desc) {
        this.description = desc;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}

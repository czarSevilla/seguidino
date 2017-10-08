package czar.seguidino.enums;

public enum FieldEnum {
    STATUS("status"),
    RESOLUTION("resolution"),
    ASSIGNEE("assignee"),
    SUBJECT("subject"),
    TYPE("type"),
    PRIORITY("priority");
    
    private String description;
    
    private FieldEnum(final String desc) {
        this.description = desc;
    }
    
    @Override
    public String toString() {
        return description;
    }
    
    public static FieldEnum findByDescription(String pDescription) {
    	for (FieldEnum current : values()) {
    		if (current.description.equals(pDescription)) {
    			return current;
    		}
    	}
    	return null;
    }
}

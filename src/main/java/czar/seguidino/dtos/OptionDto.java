package czar.seguidino.dtos;

public class OptionDto {
    private Long key;
    private String value;
    private String icon;
    private boolean selected;
    
    public OptionDto(Long pKey, String pValue, String pIcon, boolean pSelected) {
        this.key = pKey;
        this.value = pValue;
        this.icon = pIcon;
        this.selected = pSelected;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

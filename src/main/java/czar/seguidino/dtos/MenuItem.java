package czar.seguidino.dtos;

public class MenuItem {
    private String url;
    private String label;
    private String img;
    private boolean text;
    private boolean separator;
    
    public static final MenuItem SEPARATOR_MENU_ITEM = new MenuItem(null, null, false, true);
    
    public MenuItem(String pUrl, String pLabel, boolean pText, boolean pSeparator) {
        this(pUrl, pLabel, null, pText, pSeparator);
    }
    
    public MenuItem(String pUrl, String pLabel, String pImg, boolean pText, boolean pSeparator) {
        this.url = pUrl;
        this.label = pLabel;
        this.text = pText;
        this.img = pImg;
        this.separator = pSeparator;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isSeparator() {
        return separator;
    }

    public void setSeparator(boolean separator) {
        this.separator = separator;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}

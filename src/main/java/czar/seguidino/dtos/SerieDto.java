package czar.seguidino.dtos;

import java.util.ArrayList;
import java.util.List;

public class SerieDto<T> {
    private String title;
    private List<T> values;
    
    public SerieDto() {
        values = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}

package czar.seguidino.dtos;

import java.util.ArrayList;
import java.util.List;

public class ResumeGridDto {
    private String title;
    private List<SerieDto<? extends Object>> series;
    
    public ResumeGridDto() {
        series = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SerieDto<? extends Object>> getSeries() {
        return series;
    }

    public void setSeries(List<SerieDto<? extends Object>> series) {
        this.series = series;
    }
}

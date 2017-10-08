package czar.seguidino.dtos;

import java.util.Date;

public class DailyStatDto<T> {
    
    private Date day;
    private T[] values;
    
    @SafeVarargs
    public DailyStatDto(Date pDay, T... pValues) {
        this.day = pDay;
        this.values = pValues;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public T[] getValues() {
        return values;
    }

    public void setValues(T[] values) {
        this.values = values;
    }
    
    
}

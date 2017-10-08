package czar.seguidino.utils;

import java.math.BigDecimal;
import java.util.List;

import czar.seguidino.dtos.ResumeGridDto;
import czar.seguidino.dtos.SerieDto;
import czar.seguidino.enums.GridTitleEnum;

public final class GridUtils {
    
    private GridUtils() {
        
    }
    
    public static ResumeGridDto resumeGridDto(final GridTitleEnum title, final GridTitleEnum labelColumn, GridTitleEnum totalColumn, final GridTitleEnum percentColumn, final List<Object[]> data, final int total) {
        ResumeGridDto grid = new ResumeGridDto();
        grid.setTitle(title.toString());
        SerieDto<String> labels = new SerieDto<>();
        labels.setTitle(labelColumn.toString());
        SerieDto<Integer> issues = new SerieDto<>();
        issues.setTitle(totalColumn.toString());
        SerieDto<String> percents = new SerieDto<>();
        percents.setTitle(percentColumn.toString());
        
        for (Object[] row : data) {
            String label = (String) row[0];
            Long count = (Long) row[1];
            BigDecimal bgCount = new BigDecimal(String.format("%d", count));
            BigDecimal bgTotal = new BigDecimal(String.format("%d", total));
            BigDecimal percent = bgCount.divide(bgTotal, 2, BigDecimal.ROUND_HALF_DOWN);
            percent = percent.multiply(new BigDecimal("100.00"));
            percent = percent.setScale(0);
            labels.getValues().add(label);
            issues.getValues().add(count.intValue());
            percents.getValues().add(String.format("%s", percent));
        }
        
        grid.getSeries().add(labels);
        grid.getSeries().add(issues);
        grid.getSeries().add(percents);
        return grid;
    }
    
    public static ResumeGridDto resumeGridDto(final GridTitleEnum title, final GridTitleEnum labelColumn, final GridTitleEnum totalColumn, final List<Object[]> data) {
        ResumeGridDto grid = new ResumeGridDto();
        grid.setTitle(title.toString());
        
        SerieDto<String> labels = new SerieDto<>();
        labels.setTitle(labelColumn.toString());
        SerieDto<Integer> issues = new SerieDto<>();
        issues.setTitle(totalColumn.toString());
        
        for (Object[] row : data) {
            String label = (String) row[0];
            Long count = (Long) row[1];
            labels.getValues().add(label);
            issues.getValues().add(count.intValue());
        }
        
        grid.getSeries().add(labels);
        grid.getSeries().add(issues);
        
        return grid;        

    }
}

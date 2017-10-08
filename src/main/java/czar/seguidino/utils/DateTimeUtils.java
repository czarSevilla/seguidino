package czar.seguidino.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTimeUtils {
    
    private static final int DAYS_30 = 30;

    private DateTimeUtils() {}
    
    public static long hoursBetween(Date start, Date stop) {
        LocalDateTime fromDate = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
        LocalDateTime toDate = LocalDateTime.ofInstant(stop.toInstant(), ZoneId.systemDefault());
        return ChronoUnit.HOURS.between(fromDate, toDate);
    }
    
    public static Date todayMinus30DaysAgo() {
        LocalDate minus30 = LocalDate.now().minusDays(DAYS_30);
        Date date = Date.from(minus30.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    
    public static List<Date> datesBetween(Date ini, Date end) {
        LocalDate fromDate = LocalDateTime.ofInstant(ini.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate toDate = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()).toLocalDate();
        
        List<LocalDate> localdates = Stream.iterate(fromDate, date -> date.plusDays(1)) 
                .limit(ChronoUnit.DAYS.between(fromDate, toDate) + 1).collect(Collectors.toList());
        
        List<Date> dates = localdates
                .stream()
                .map(localDate -> Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .collect(Collectors.toList());
        
        return dates;
    }
    
    public static Date addDays(Date base, long days) {
        LocalDate fromDate = LocalDateTime.ofInstant(base.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return Date.from(fromDate.plusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant());
        
    }
    
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static LocalDate toLocalDate(java.sql.Date date) {
        Date d = new Date(date.getTime());
        return toLocalDate(d);
    }
    
    public static Set<LocalDate> rangeMinusInDays(LocalDate end, int days) {
        LocalDate ini = end.minusDays(days - 1L);

        Set<LocalDate> dates = new TreeSet<>(); 

        while(ini.isBefore(end)) {
            dates.add(ini);
            ini = ini.plusDays(1);
        }
        dates.add(end);

        return dates;
    }
}

package czar.seguidino.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HumanizeTimeUtils {
    private enum TimeRange {
        MINUTES,
        ONE_DAY,
        TWO_DAY,
        ONE_WEEK;
    };

    private HumanizeTimeUtils() {

    }

    public static String humanizedTime(Date date) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        ldt = ldt.plusMinutes(1).minusNanos(1).withSecond(0).withNano(0);
        return humanizeInTimeRange(ldt, TimeRange.MINUTES);
    }

    private static String humanizeInTimeRange(LocalDateTime date, TimeRange timeRange) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(1).minusNanos(1).withSecond(0).withNano(0);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter weekFormatter = DateTimeFormatter.ofPattern("EEEE hh:mm a");
        DateTimeFormatter standardFormatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
        switch(timeRange) {
            case MINUTES:
                LocalDateTime minus1Hour = now.minusHours(1);
                if (minus1Hour.isBefore(date)) {
                    Duration duration = Duration.between(date, now);
                    return String.format("%d minutes ago", duration.toMinutes()); 
                } else if (minus1Hour.compareTo(date) == 0) {
                    return "One hour ago";
                } else {
                    return humanizeInTimeRange(date, TimeRange.ONE_DAY);
                }
            case ONE_DAY:
                LocalDateTime minus1Days = now.withHour(0).withMinute(0);
                if (minus1Days.isBefore(date)) {
                    return String.format("Today %s", timeFormatter.format(date));
                } else {
                    return humanizeInTimeRange(date, TimeRange.TWO_DAY);
                }
            case TWO_DAY:
                LocalDateTime minus2Days = now.minusDays(1).withHour(0).withMinute(0);
                if (minus2Days.isBefore(date)) {
                    return String.format("Yesterday %s", timeFormatter.format(date));
                } else {
                    return humanizeInTimeRange(date, TimeRange.ONE_WEEK);
                }
            case ONE_WEEK:
            default:
                LocalDateTime minus1Week = now.withHour(0).withMinute(0).minusWeeks(1);
                if (minus1Week.isBefore(date)) {
                    return String.format("Last %s", weekFormatter.format(date));
                } else {
                    return standardFormatter.format(date);
                }
        }
    }
}

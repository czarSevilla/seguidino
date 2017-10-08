package czar.seguidino.services;

import java.time.LocalDate;
import java.util.List;

import czar.seguidino.dtos.DailyStatDto;

public interface StatService {

    List<DailyStatDto<Long>> last30DaysByProject(String keyProject, LocalDate base);
}

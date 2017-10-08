package czar.seguidino.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import czar.seguidino.dtos.DailyStatDto;
import czar.seguidino.entities.Project;
import czar.seguidino.repositories.ProjectRepository;
import czar.seguidino.repositories.ResumeGridCustomRepository;
import czar.seguidino.services.StatService;
import czar.seguidino.utils.DateTimeUtils;

@Service("statService")
public class StatServiceImpl implements StatService {
    
    private static final int TWENTY_NINE = 29;
    private static final int THIRTY = 30;
    
    private Logger logger = LoggerFactory.getLogger(StatServiceImpl.class);
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ResumeGridCustomRepository resumeGridCustomRepository;

    @Override
    public List<DailyStatDto<Long>> last30DaysByProject(String keyProject, LocalDate base) {
        logger.info(String.format("last30DaysByProject(%s, %s)", keyProject, base));
        
        Project p = projectRepository.findOneByKeyProject(keyProject);
        
        Set<LocalDate> dates = DateTimeUtils.rangeMinusInDays(base, THIRTY);
        
        Date end =  DateTimeUtils.toDate(base);
        Date start = DateTimeUtils.toDate(base.minusDays(TWENTY_NINE));
        
        Long[] bases = resumeGridCustomRepository.countAccumulatedByIdProjectAndDate(p.getIdProject(), start);
        
        logger.info(String.format("[%d, %d] = resumeGridCustomRepository.countAccumulatedByIdProjectAndDate(%d, %s)",
                bases[0], bases[1], p.getIdProject(), base.minusDays(TWENTY_NINE)));
        
        List<Object[]> created = resumeGridCustomRepository.groupByIdProjectAndCreationOn(p.getIdProject(), start, end);
        logger.info(String.format("list[size=%d] = resumeGridCustomRepository.groupByIdProjectAndCreationOn(%d, %s, %s)", 
                created.size(), p.getIdProject(), base.minusDays(TWENTY_NINE), base));
        
        List<Object[]> resolved = resumeGridCustomRepository.groupByIdProjectAndResolvedOn(p.getIdProject(), start, end);
        logger.info(String.format("list[size=%d] = resumeGridCustomRepository.groupByIdProjectAndResolvedOn(%d, %s, %s)", 
                resolved.size(), p.getIdProject(), base.minusDays(TWENTY_NINE), base));
        
        List<DailyStatDto<Long>> resultList = new ArrayList<>();
        
        Long counter1 = bases[0];
        Long counter2 = bases[1];

        Map<LocalDate, Long> map1 = new TreeMap<>();
        for (Object[] row : created) {
            LocalDate d = DateTimeUtils.toLocalDate((java.sql.Date) row[0]);
            Long l = (Long) row[1]; 
            map1.put(d, l);
        }

        Map<LocalDate, Long> map2 = new TreeMap<>();
        for (Object[] row : resolved) {
            LocalDate d = DateTimeUtils.toLocalDate((java.sql.Date) row[0]);
            Long l = (Long) row[1]; 
            map2.put(d, l);
        }

        for (LocalDate ld : dates) {
            Long l1 = map1.get(ld);
            if (l1 != null) {
                counter1 += l1;
            }
            Long l2 = map2.get(ld);
            if (l2 != null) {
                counter2 += l2;
            }
            logger.info(String.format("DayliStatDto[%s, %d, %d]", ld, counter1, counter2));
            Date d = DateTimeUtils.toDate(ld);
            resultList.add(new DailyStatDto<Long>(d, new Long(counter1), new Long(counter2)));
        }
        return resultList;
    }

}

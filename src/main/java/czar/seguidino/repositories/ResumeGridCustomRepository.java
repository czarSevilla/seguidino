package czar.seguidino.repositories;

import java.util.Date;
import java.util.List;

public interface ResumeGridCustomRepository {
    
    int countUnresolvedByProject(Long idProject);
    
    int countTotalByProject(Long idProject);
    
    List<Object[]> groupByStatus(Long idProject);
    
    List<Object[]> groupUnresolvedByPriority(Long idProject);
    
    List<Object[]> groupUnresolvedByAssignee(Long idProject);
    
    List<Object[]> groupUnresolvedByType(Long idProject);
    
    List<Object[]> groupUnresolvedByComponent(Long idProject);
    
    Long[] countAccumulatedByIdProjectAndDate(Long idProject, Date date);
    
    List<Object[]> groupByIdProjectAndCreationOn(Long idProject, Date begin, Date end);
    
    List<Object[]> groupByIdProjectAndResolvedOn(Long idProject, Date begin, Date end);
}

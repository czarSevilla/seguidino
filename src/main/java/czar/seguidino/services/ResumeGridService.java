package czar.seguidino.services;

import czar.seguidino.dtos.ResumeGridDto;

public interface ResumeGridService {
    
    int countUnresolvedByProject(Long idProject);
    
    int countTotalByProject(Long idProject);
    
    ResumeGridDto buildProjectSummary(Long idProject, int total);
    
    ResumeGridDto buildGridByPriority(Long idProject, int totalUnresolved);
    
    ResumeGridDto buildGridByComponent(Long idProject);
    
    ResumeGridDto buildGridByAssignee(Long idProject, int totalUnresolved);
    
    ResumeGridDto buildGridByType(Long idProject, int totalUnresolved);
    
    
}

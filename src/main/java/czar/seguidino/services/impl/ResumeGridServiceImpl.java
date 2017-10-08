package czar.seguidino.services.impl;

import static czar.seguidino.enums.GridTitleEnum.ASSIGNEE;
import static czar.seguidino.enums.GridTitleEnum.COMPONENT;
import static czar.seguidino.enums.GridTitleEnum.ISSUES;
import static czar.seguidino.enums.GridTitleEnum.PERCENTAGE;
import static czar.seguidino.enums.GridTitleEnum.PRIORITY;
import static czar.seguidino.enums.GridTitleEnum.STATUS;
import static czar.seguidino.enums.GridTitleEnum.TITLE_COMPONENT;
import static czar.seguidino.enums.GridTitleEnum.TITLE_SUMMARY;
import static czar.seguidino.enums.GridTitleEnum.TITLE_UNRESOLVED_ASSIGNEE;
import static czar.seguidino.enums.GridTitleEnum.TITLE_UNRESOLVED_PRIORITY;
import static czar.seguidino.enums.GridTitleEnum.TITLE_UNRESOLVED_TYPE;
import static czar.seguidino.enums.GridTitleEnum.TYPE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import czar.seguidino.dtos.ResumeGridDto;
import czar.seguidino.repositories.ResumeGridCustomRepository;
import czar.seguidino.services.ResumeGridService;
import czar.seguidino.utils.GridUtils;

@Service("resumeGridService")
public class ResumeGridServiceImpl implements ResumeGridService {
    
    @Autowired
    private ResumeGridCustomRepository resumeGridCustomRepository; 
    
    @Override
    public int countTotalByProject(final Long idProject) {
        return resumeGridCustomRepository.countTotalByProject(idProject);
    }

    @Override
    public int countUnresolvedByProject(final Long idProject) {
        return resumeGridCustomRepository.countUnresolvedByProject(idProject);
    }

    @Override
    public ResumeGridDto buildProjectSummary(final Long idProject, final int total) {
        List<Object[]> data = resumeGridCustomRepository.groupByStatus(idProject);
        return GridUtils.resumeGridDto(TITLE_SUMMARY, STATUS, ISSUES, PERCENTAGE, data, total); 
    }

    @Override
    public ResumeGridDto buildGridByPriority(final Long idProject, final int totalUnresolved) {
        List<Object[]> data = resumeGridCustomRepository.groupUnresolvedByPriority(idProject);
        return GridUtils.resumeGridDto(TITLE_UNRESOLVED_PRIORITY, PRIORITY, ISSUES, PERCENTAGE, data, totalUnresolved);
    }

    @Override
    public ResumeGridDto buildGridByComponent(Long idProject) {
        List<Object[]> data = resumeGridCustomRepository.groupUnresolvedByComponent(idProject);
        return GridUtils.resumeGridDto(TITLE_COMPONENT, COMPONENT, ISSUES, data);        
    }

    @Override
    public ResumeGridDto buildGridByAssignee(Long idProject, int totalUnresolved) {
        List<Object[]> data = resumeGridCustomRepository.groupUnresolvedByAssignee(idProject);
        return GridUtils.resumeGridDto(TITLE_UNRESOLVED_ASSIGNEE, ASSIGNEE, ISSUES, PERCENTAGE, data, totalUnresolved);
    }

    @Override
    public ResumeGridDto buildGridByType(Long idProject, int totalUnresolved) {
        List<Object[]> data = resumeGridCustomRepository.groupUnresolvedByType(idProject);
        return GridUtils.resumeGridDto(TITLE_UNRESOLVED_TYPE, TYPE, ISSUES, PERCENTAGE, data, totalUnresolved);
    }

}

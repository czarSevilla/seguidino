package czar.seguidino.repositories;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import czar.seguidino.entities.Issue;


public interface IssueCustomRepository {
    Long getMaxCounterByIdProject(Long idProject);
    
    Page<Issue> findByQueryAndParams(String query, Map<String, Object> params, Pageable pageable);
}

package czar.seguidino.repositories.impl;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Issue;
import czar.seguidino.repositories.IssueCustomRepository;

@Repository("issueCustomRepository")
public class IssueCustomRepositoryImpl implements IssueCustomRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getMaxCounterByIdProject(Long idProject) {
        TypedQuery<Long> query = em.createQuery("select max(i.counter) from Issue i where i.idProject = ?", Long.class);
        return query.setParameter(1, idProject).getSingleResult();
    }

    @Override
    public Page<Issue> findByQueryAndParams(String sql, Map<String, Object> params, Pageable pageable) {
        TypedQuery<Issue> query = em.createQuery(sql, Issue.class);        
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        int total = query.getResultList().size();
        query.setFirstResult(pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
        return new PageImpl<>(query.getResultList(), pageable, total);
    }

}

package czar.seguidino.repositories.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import czar.seguidino.repositories.ResumeGridCustomRepository;

@Repository("resumeGridCustomRepository")
public class ResumeGridCustomRepositoryImpl implements ResumeGridCustomRepository {

    @Autowired
    private EntityManager em;
    
    @Override
    public int countUnresolvedByProject(Long idProject) {
        String jql = "select count(i) from Issue i where i.idProject = ? and i.resolved is null";
        TypedQuery<Long> query = em.createQuery(jql, Long.class);
        query.setParameter(1, idProject);
        Long total = query.getSingleResult();
        if (total != null) {
            return total.intValue();
        }
        return 0;
    }

    @Override
    public int countTotalByProject(Long idProject) {
        String jql = "select count(i) from Issue i where i.idProject = ?";
        TypedQuery<Long> query = em.createQuery(jql, Long.class);
        query.setParameter(1, idProject);
        Long total = query.getSingleResult();
        if (total != null) {
            return total.intValue();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupByStatus(Long idProject) {
        String jql = "select st.name, count(i) from Issue i left join i.issueStatus st where i.idProject = ? group by st.idIssueStatus order by st.idIssueStatus";
        Query query = em.createQuery(jql);
        query.setParameter(1, idProject);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupUnresolvedByPriority(Long idProject) {
        String jql = "select pr.name, count(i) from Issue i left join i.issuePriority pr where i.idProject = ? and i.resolved is null group by pr.idIssuePriority order by pr.idIssuePriority";
        Query query = em.createQuery(jql);
        query.setParameter(1, idProject);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupUnresolvedByAssignee(Long idProject) {
        String jql = "select a.name, count(i) from Issue i left join i.assignee a where i.idProject = ? and i.resolved is null group by a.idUser order by a.idUser";
        Query query = em.createQuery(jql);
        query.setParameter(1, idProject);
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupUnresolvedByType(Long idProject) {
        String jql = "select t.name, count(i) from Issue i left join i.issueType t where i.idProject = ? and i.resolved is null group by t.idIssueType order by t.idIssueType";
        Query query = em.createQuery(jql);
        query.setParameter(1, idProject);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupUnresolvedByComponent(Long idProject) {
        String jql = "select pc.name, count(i) from Issue i left join i.projectComponents pc ";
        jql += "where i.idProject = ? and i.resolved is null group by pc.idComponent order by pc.idComponent";
        Query query = em.createQuery(jql);
        query.setParameter(1, idProject);
        return query.getResultList();
    }

    @Override
    public Long[] countAccumulatedByIdProjectAndDate(Long idProject, Date date) {
        String jql1 = "select count(i.idIssue) from Issue i where i.idProject = ?1 and i.creation < ?2";
        String jql2 = "select count(j.idIssue) from Issue j where j.idProject = ?1 and j.resolved < ?2";
        
        TypedQuery<Long> query1 = em.createQuery(jql1, Long.class);
        TypedQuery<Long> query2 = em.createQuery(jql2, Long.class);
        
        query1.setParameter(1, idProject);
        query1.setParameter(2, date, TemporalType.DATE);
        query2.setParameter(1, idProject);
        query2.setParameter(2, date, TemporalType.DATE);
        
        Long created = query1.getSingleResult();
        Long resolved = query2.getSingleResult();
        
        return new Long[]{created, resolved};
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupByIdProjectAndCreationOn(Long idProject, Date begin, Date end) {
        String jql = "select cast(i.creation as date), count(i.idIssue) from Issue i where i.idProject = ?1 and cast(i.creation as date) between ?2 and ?3 group by cast(i.creation as date)";
        Query query = em.createQuery(jql);
        int idx = 1;
        query.setParameter(idx++, idProject);
        query.setParameter(idx++, begin, TemporalType.DATE);
        query.setParameter(idx, end, TemporalType.DATE);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> groupByIdProjectAndResolvedOn(Long idProject, Date begin, Date end) {
        String jql = "select cast(i.resolved as date), count(i.idIssue) from Issue i where i.idProject = ?1 and cast(i.resolved as date) between ?2 and ?3 group by cast(i.resolved as date)";
        Query query = em.createQuery(jql);
        int idx = 1;
        query.setParameter(idx++, idProject);
        query.setParameter(idx++, begin, TemporalType.DATE);
        query.setParameter(idx, end, TemporalType.DATE);
        return query.getResultList();
    }

}

package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Issue;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long>, QueryByExampleExecutor<Issue> {
       
    Issue findOneByKeyIssue(String keyIssue);
}

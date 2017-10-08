package czar.seguidino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Comment;

@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByIdIssueOrderByCreation(Long idIssue);
}

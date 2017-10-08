package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Filter;

@Repository("filterRepository")
public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter findOneByIdProjectAndName(Long idProject, String name);
}

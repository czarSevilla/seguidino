package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.Parameter;

@Repository("parameterRepository")
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Parameter findOneByKey(String key);
}


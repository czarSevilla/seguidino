package czar.seguidino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import czar.seguidino.entities.FileReference;

@Repository("fileReferenceRepository")
public interface FileReferenceRepository extends JpaRepository<FileReference, Long> {

}

package czar.seguidino.services;

import java.io.IOException;
import java.util.List;

import czar.seguidino.dtos.FileDto;
import czar.seguidino.dtos.MenuItem;
import czar.seguidino.dtos.OptionDto;
import czar.seguidino.entities.Project;
import czar.seguidino.entities.ProjectComponent;

public interface ProjectService {
    Project findByKey(String key, String username);
    List<MenuItem> buildProjectMenuItems(String username);
    Project findByKey(String key);
    List<OptionDto> listProjectOptions(String username);
    List<OptionDto> listComponentsOptions(Long idProject);
    List<OptionDto> listAssigneesOptions(Long idProject);
    Project findById(Long idProject);
    List<ProjectComponent> findComponentsByProject(Long idProject);
    List<Project> findAll();
    String updateAvatar(Long idProject, String filename, String contentType, Long size, byte[] content);
    FileDto findAvatar(String projectKey, Long idFileReference) throws IOException;
}

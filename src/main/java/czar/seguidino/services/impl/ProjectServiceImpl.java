package czar.seguidino.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import czar.seguidino.dtos.FileDto;
import czar.seguidino.dtos.MenuItem;
import czar.seguidino.dtos.OptionDto;
import czar.seguidino.entities.FileReference;
import czar.seguidino.entities.Project;
import czar.seguidino.entities.ProjectAccess;
import czar.seguidino.entities.ProjectComponent;
import czar.seguidino.entities.ProjectMember;
import czar.seguidino.entities.ProjectRole;
import czar.seguidino.entities.User;
import czar.seguidino.enums.IssueRoleEnum;
import czar.seguidino.enums.ParameterEnum;
import czar.seguidino.repositories.FileReferenceRepository;
import czar.seguidino.repositories.ParameterRepository;
import czar.seguidino.repositories.ProjectAccessReporitory;
import czar.seguidino.repositories.ProjectComponentRepository;
import czar.seguidino.repositories.ProjectMemberRepository;
import czar.seguidino.repositories.ProjectRepository;
import czar.seguidino.repositories.UserRepository;
import czar.seguidino.services.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProjectAccessReporitory projectAccessReporitory;
    
    @Autowired
    private ProjectComponentRepository projectComponentRepository;
    
    @Autowired
    private ProjectMemberRepository projectMemberRepository;
    
    @Autowired
    private ParameterRepository parameterRepository;
    
    @Autowired
    private FileReferenceRepository fileReferenceRepository;
    
    public Project findByKey(String key, String username) {
        Project project = projectRepository.findOneByKeyProject(key);
        User user = userRepository.findByUsername(username);
        
        ProjectAccess last = projectAccessReporitory.findOneByIdUserAndProject(user.getIdUser(), project);
        
        if (last != null) {
            last.setAccess(new Date());
        } else {
            last = new ProjectAccess();
            last.setProject(project);
            last.setIdUser(user.getIdUser());
            last.setAccess(new Date());
        }        
        projectAccessReporitory.save(last);
        
        return project;
    }

    @Override
    public List<MenuItem> buildProjectMenuItems(String username) {
        List<MenuItem> items = new ArrayList<>();
        User user = userRepository.findByUsername(username);
        
        
        PageRequest pageable = new PageRequest(0, 6, Sort.Direction.DESC, "access");
        Page<Project> page = projectAccessReporitory.findProjectsByIdUser(user.getIdUser(), pageable);
        
        if (page.getContent().size() > 0) {
            items.add(new MenuItem(null, "Current", true, false));
            Project current = page.getContent().get(0);
            items.add(projectToMenuItem(current));
            items.add(MenuItem.SEPARATOR_MENU_ITEM);
            if (page.getContent().size() > 1) {
                List<Project> last = page.getContent().subList(1, page.getContent().size());
                items.add(new MenuItem(null, "Recent", true, false));
                for (Project p : last) {
                    items.add(projectToMenuItem(p));
                }
                items.add(MenuItem.SEPARATOR_MENU_ITEM);
            }
        }
        return items;
    }
    
    private MenuItem projectToMenuItem(Project project) {
        String url = String.format("/projects/%s", project.getKeyProject());
        String name = project.getName();
        String avatar = project.getAvatar();
        return new MenuItem(url, name, avatar, false, false);
    }

    @Override
    public Project findByKey(String key) {
        return projectRepository.findOneByKeyProject(key);
    }

    @Override
    public List<OptionDto> listProjectOptions(String username) {
        User user = userRepository.findByUsername(username);
        
        PageRequest pageable = new PageRequest(0, 1, Sort.Direction.DESC, "access");
        Page<Project> page = projectAccessReporitory.findProjectsByIdUser(user.getIdUser(), pageable);
        
        final Project current = page.getContent().size() > 0 ? page.getContent().get(0) : null;
        
        List<Project> projects = projectRepository.findByIdUser(user.getIdUser());
        
        List<OptionDto> options = null;
        
        if (projects.size() > 0) {
            options = projects
                        .stream()
                            .map(p -> new OptionDto(
                                          p.getIdProject()
                                        , p.getName()
                                        , p.getAvatar()
                                        , current != null && p.getIdProject().equals(current.getIdProject())))
                            .collect(Collectors.toList());
        } else {
            options = new ArrayList<>();
        }
        
        return options;
    }

    @Override
    public List<OptionDto> listComponentsOptions(Long idProject) {
        List<ProjectComponent> components = projectComponentRepository.findByIdProject(idProject);
        List<OptionDto> options = components
                .stream()
                .map(pc -> new OptionDto(pc.getIdComponent(), pc.getName(), null, false))
                .collect(Collectors.toList());
        return options;
    }

    @Override
    public List<OptionDto> listAssigneesOptions(Long idProject) {
        ProjectRole devs = new ProjectRole();
        devs.setIdRole(IssueRoleEnum.DEVELOPER.getId());
        
        ProjectRole admins = new ProjectRole();
        admins.setIdRole(IssueRoleEnum.ADMINISTRATOR.getId());
        
        List<ProjectMember> developers = projectMemberRepository.findByIdProjectAndRoles(idProject, Arrays.asList(admins, devs));
        
        List<OptionDto> options = developers
                .stream()
                .map(dev -> new OptionDto(dev.getUser().getIdUser(), dev.getUser().getName(), dev.getUser().getAvatar(), false))
                .collect(Collectors.toList());
        return options;
    }

    @Override
    public Project findById(Long idProject) {
        return projectRepository.findOne(idProject);
    }

    @Override
    public List<ProjectComponent> findComponentsByProject(Long idProject) {
        List<ProjectComponent> components = projectComponentRepository.findByIdProject(idProject);
        return components;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    
    @Override
    public String updateAvatar(Long idProject, String filename, String contentType, Long size, byte[] content) {
        Project p = projectRepository.findOne(idProject);
        
        Date now = new Date();
        
        String basepath = parameterRepository.findOneByKey(ParameterEnum.PROJECT_AVATAR_BASE_PATH.toString()).getValue();
        
        String relativePath = String.format("%s", p.getKeyProject());
        
        FileReference fileReference = new FileReference();
        fileReference.setContentType(contentType);
        fileReference.setOriginalName(filename);
        fileReference.setSize(size);
        fileReference.setRelativePath(relativePath);
        fileReference.setCreation(now);
        
        
        fileReferenceRepository.save(fileReference);
        
        String buildName = String.format("%d_avatar.%s", fileReference.getIdFileReference()
                                        , filename.substring(filename.lastIndexOf(".") + 1).toLowerCase());
        
        try {
            String fullpath = String.format("%s%s%s", 
                    basepath, File.separator, relativePath);
            File fileFullPath = new File(fullpath);
            if (!fileFullPath.exists()) {
                fileFullPath.mkdirs();
            }
            Path path = Paths.get(String.format("%s%s%s", fullpath, File.separator, buildName));
            Files.write(path, content);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        fileReference.setFileName(buildName);
        fileReferenceRepository.save(fileReference);
        
        String urlAvatar = String.format("/projects/%s/avatar/%d", p.getKeyProject(), fileReference.getIdFileReference());
        
        p.setAvatar(urlAvatar);
        projectRepository.save(p);
        
        return urlAvatar;
    }

    @Override
    public FileDto findAvatar(String projectKey, Long idFileReference) throws IOException {
        FileDto fileDto = new FileDto();
        
        FileReference fileReference = fileReferenceRepository.findOne(idFileReference);
        
        String basepath = parameterRepository.findOneByKey(ParameterEnum.PROJECT_AVATAR_BASE_PATH.toString()).getValue();
        
        String fullpath = String.format("%s%s%s%s%s", basepath, File.separator, fileReference.getRelativePath(), File.separator, fileReference.getFileName());
        
        byte[] bytes = Files.readAllBytes(Paths.get(fullpath));
        
        fileDto.setBytes(bytes);
        System.out.println(String.format("avatar.contentType=%s", fileReference.getContentType()));
        fileDto.setContentType(MediaType.IMAGE_PNG);
        
        return fileDto;
    }
}

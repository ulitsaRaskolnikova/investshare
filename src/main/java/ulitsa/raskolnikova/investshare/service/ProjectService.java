package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.dto.Project;
import ulitsa.raskolnikova.investshare.dto.ProjectResponse;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.NoPermissionException;
import ulitsa.raskolnikova.investshare.mapper.ProjectMapper;
import ulitsa.raskolnikova.investshare.repository.ProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public Project createProject(Project project) {
        ProjectEntity projectEntity = projectMapper.toEntity(project);
        projectEntity.getProjectAccount().setProject(projectEntity);
        return projectMapper.toDto(projectRepository.save(projectEntity));
    }

    public Project getProjectById(int id) throws ChangeSetPersister.NotFoundException {
        return projectMapper.toDto(projectRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findByIsPublicTrue().stream()
                .map(projectMapper::toDto)
                .map(projectMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public Project updateProject(Project project) throws ChangeSetPersister.NotFoundException {
        ProjectEntity projectEntity = projectRepository.findById(project.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        projectMapper.updateProjectFromDto(project, projectEntity);
        projectEntity.getProjectAccount().setId(project.getId());
        return projectMapper.toDto(projectRepository.save(projectEntity));
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

}

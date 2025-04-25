package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.dto.project.Project;
import ulitsa.raskolnikova.investshare.dto.project.ProjectResponse;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.mapper.project.ProjectMapper;
import ulitsa.raskolnikova.investshare.repository.ProjectRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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

    public Project getProjectById(int id) {
        return projectMapper.toDto(projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No project found with id: " + id)));
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

    public boolean isProjectAuthor(Integer projectId) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project project = getProjectById(projectId);
        return Objects.equals(project.getAuthorId(), user.getId());
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

}

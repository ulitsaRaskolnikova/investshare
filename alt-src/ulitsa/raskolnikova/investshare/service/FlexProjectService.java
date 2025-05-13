package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.dto.project.FlexProject;
import ulitsa.raskolnikova.investshare.dto.project.FlexProjectResponse;
import ulitsa.raskolnikova.investshare.entity.FlexProjectEntity;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.mapper.project.FlexProjectMapper;
import ulitsa.raskolnikova.investshare.repository.FlexProjectRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FlexProjectService {
    private final FlexProjectRepository projectRepository;
    private final FlexProjectMapper projectMapper;

    public FlexProject createFlexProject(FlexProject project) {
        FlexProjectEntity projectEntity = projectMapper.toEntity(project);
        projectEntity.getFlexProjectAccount().setFlexProject(projectEntity);
        return projectMapper.toDto(projectRepository.save(projectEntity));
    }

    public FlexProject getFlexProjectById(int id) {
        return projectMapper.toDto(projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No project found with id: " + id)));
    }

    public List<FlexProjectResponse> getAllFlexProjects() {
        return projectRepository.findByIsPublicTrue().stream()
                .map(projectMapper::toDto)
                .map(projectMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public FlexProject updateFlexProject(FlexProject project) throws ChangeSetPersister.NotFoundException {
        FlexProjectEntity projectEntity = projectRepository.findById(project.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        projectMapper.updateFlexProjectFromDto(project, projectEntity);
        projectEntity.getFlexProjectAccount().setId(project.getId());
        return projectMapper.toDto(projectRepository.save(projectEntity));
    }

    public boolean isFlexProjectAuthor(Integer projectId) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FlexProject project = getFlexProjectById(projectId);
        return Objects.equals(project.getAuthorId(), user.getId());
    }

    public void deleteFlexProject(int id) {
        projectRepository.deleteById(id);
    }

}

package ulitsa.raskolnikova.investshare.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ulitsa.raskolnikova.investshare.dto.project.Project;
import ulitsa.raskolnikova.investshare.dto.project.ProjectResponse;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.entity.account.IpProjectAccountEntity;
import ulitsa.raskolnikova.investshare.mapper.project.ProjectMapper;
import ulitsa.raskolnikova.investshare.repository.ProjectRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    private ProjectEntity projectEntity;
    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        projectEntity = new ProjectEntity();
        projectEntity.setId(1);

        projectEntity.setProjectAccount(new IpProjectAccountEntity());

        project = new Project();
        project.setId(1);
        project.setAuthorId(1);

        UserEntity user = new UserEntity();
        user.setId(1);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void createProject_shouldReturnCreatedProject() {
        when(projectMapper.toEntity(project)).thenReturn(projectEntity);
        when(projectRepository.save(projectEntity)).thenReturn(projectEntity);
        when(projectMapper.toDto(projectEntity)).thenReturn(project);

        Project result = projectService.createProject(project);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        verify(projectRepository).save(projectEntity);
    }

    @Test
    void getProjectById_shouldReturnProject_whenFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.of(projectEntity));
        when(projectMapper.toDto(projectEntity)).thenReturn(project);

        Project result = projectService.getProjectById(1);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void getProjectById_shouldThrow_whenNotFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.getProjectById(1))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No project found with id");
    }

    @Test
    void getAllProjects_shouldReturnListOfResponses() {
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setId(2);
        List<ProjectEntity> entities = List.of(projectEntity, projectEntity2);

        when(projectRepository.findByIsPublicTrue()).thenReturn(entities);
        when(projectMapper.toDto(any(ProjectEntity.class))).thenReturn(project);
        when(projectMapper.toResponseDto(any(Project.class))).thenReturn(new ProjectResponse(
                null, null, null, null, null, null,
                null, null, null, null, null
        ));

        List<ProjectResponse> result = projectService.getAllProjects();

        assertThat(result).hasSize(2);
        verify(projectRepository).findByIsPublicTrue();
    }

    @Test
    void updateProject_shouldUpdateSuccessfully() throws Exception {
        when(projectRepository.findById(1)).thenReturn(Optional.of(projectEntity));
        doNothing().when(projectMapper).updateProjectFromDto(project, projectEntity);
        when(projectRepository.save(projectEntity)).thenReturn(projectEntity);
        when(projectMapper.toDto(projectEntity)).thenReturn(project);

        Project result = projectService.updateProject(project);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void updateProject_shouldThrow_whenNotFound() {
        when(projectRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> projectService.updateProject(project))
                .isInstanceOf(ChangeSetPersister.NotFoundException.class);
    }

    @Test
    void isProjectAuthor_shouldReturnTrue_whenUserIsAuthor() {
        when(projectRepository.findById(1)).thenReturn(Optional.of(projectEntity));
        when(projectMapper.toDto(projectEntity)).thenReturn(project);

        boolean result = projectService.isProjectAuthor(1);

        assertThat(result).isTrue();
    }

    @Test
    void isProjectAuthor_shouldReturnFalse_whenUserIsNotAuthor() {
        UserEntity otherUser = new UserEntity();
        otherUser.setId(2);
        when(authentication.getPrincipal()).thenReturn(otherUser);
        when(projectRepository.findById(1)).thenReturn(Optional.of(projectEntity));
        when(projectMapper.toDto(projectEntity)).thenReturn(project);

        boolean result = projectService.isProjectAuthor(1);

        assertThat(result).isFalse();
    }

    @Test
    void deleteProject_shouldCallRepositoryDelete() {
        doNothing().when(projectRepository).deleteById(1);

        projectService.deleteProject(1);

        verify(projectRepository, times(1)).deleteById(1);
    }
}


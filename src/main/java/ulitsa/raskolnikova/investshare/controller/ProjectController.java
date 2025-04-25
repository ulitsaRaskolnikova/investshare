package ulitsa.raskolnikova.investshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ulitsa.raskolnikova.investshare.dto.project.Project;
import ulitsa.raskolnikova.investshare.dto.project.ProjectResponse;
import ulitsa.raskolnikova.investshare.dto.project.ResponseValue;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.mapper.project.ProjectMapper;
import ulitsa.raskolnikova.investshare.service.ProjectService;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseValue> getProjectById(
            @PathVariable int id,
            @AuthenticationPrincipal UserEntity user
    ) {
        Project project = projectService.getProjectById(id);
        if (project.getIsPublic() && (user == null || !Objects.equals(user.getId(), project.getAuthorId()))) {
            return ResponseEntity.ok(projectMapper.toResponseDto(project));
        }
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project,
            @AuthenticationPrincipal UserEntity user
    ) {
        project.setAuthorId(user.getId());
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PreAuthorize("isAuthenticated() and @projectService.isProjectAuthor(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseValue> updateProject(
            @PathVariable int id,
            @RequestBody Project project
    ) throws ChangeSetPersister.NotFoundException {
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProject(project));
    }

    @PreAuthorize("isAuthenticated() and @projectService.isProjectAuthor(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable int id
    ) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}

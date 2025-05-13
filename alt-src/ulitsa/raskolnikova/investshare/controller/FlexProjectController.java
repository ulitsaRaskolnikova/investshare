package ulitsa.raskolnikova.investshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ulitsa.raskolnikova.investshare.dto.project.FlexProject;
import ulitsa.raskolnikova.investshare.dto.project.FlexProjectResponse;
import ulitsa.raskolnikova.investshare.dto.project.ResponseValue;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.mapper.project.FlexProjectMapper;
import ulitsa.raskolnikova.investshare.service.FlexProjectService;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class FlexProjectController {
    private final FlexProjectService projectService;
    private final FlexProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<FlexProjectResponse>> getAllFlexProjects() {
        return ResponseEntity.ok(projectService.getAllFlexProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseValue> getFlexProjectById(
            @PathVariable int id,
            @AuthenticationPrincipal UserEntity user
    ) {
        FlexProject project = projectService.getFlexProjectById(id);
        if (project.getIsPublic() && (user == null || !Objects.equals(user.getId(), project.getAuthorId()))) {
            return ResponseEntity.ok(projectMapper.toResponseDto(project));
        }
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<FlexProject> createFlexProject(
            @RequestBody FlexProject project,
            @AuthenticationPrincipal UserEntity user
    ) {
        project.setAuthorId(user.getId());
        return ResponseEntity.ok(projectService.createFlexProject(project));
    }

    @PreAuthorize("isAuthenticated() and @projectService.isFlexProjectAuthor(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseValue> updateFlexProject(
            @PathVariable int id,
            @RequestBody FlexProject project
    ) throws ChangeSetPersister.NotFoundException {
        project.setId(id);
        return ResponseEntity.ok(projectService.updateFlexProject(project));
    }

    @PreAuthorize("isAuthenticated() and @projectService.isFlexProjectAuthor(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlexProject(
            @PathVariable int id
    ) {
        projectService.deleteFlexProject(id);
        return ResponseEntity.ok().build();
    }
}

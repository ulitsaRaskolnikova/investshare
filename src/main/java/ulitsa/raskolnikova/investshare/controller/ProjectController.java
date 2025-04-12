package ulitsa.raskolnikova.investshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ulitsa.raskolnikova.investshare.dto.Project;
import ulitsa.raskolnikova.investshare.dto.ProjectResponse;
import ulitsa.raskolnikova.investshare.dto.ResponseValue;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;
import ulitsa.raskolnikova.investshare.mapper.ProjectMapper;
import ulitsa.raskolnikova.investshare.service.ProjectService;
import ulitsa.raskolnikova.investshare.service.UserEntityService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final UserEntityService userEntityService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseValue> getProjectById(@PathVariable int id,
                                                        @RequestHeader(required = false) String authorization)
            throws ChangeSetPersister.NotFoundException, InvalidBasicAuthorizationException {
        UserEntity userEntity = userEntityService.getUserEntity(authorization);
        Project project = projectService.getProjectById(id);
        if (userEntity == null && project.getIsPublic()) {
            return ResponseEntity.ok(projectMapper.toResponseDto(project));
        }
        if (userEntity == null) {
            throw new InvalidBasicAuthorizationException("You are not authorized to view this project");
        }
        if (project.getAuthorId().equals(userEntity.getId())) {
            return ResponseEntity.ok(project);
        }
        throw new InvalidBasicAuthorizationException("You are not owner of this project");
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project,
                                                 @RequestHeader String authorization)
            throws InvalidBasicAuthorizationException {
        UserEntity userEntity = userEntityService.getUserEntity(authorization);
        if (userEntity == null || !userEntity.getId().equals(project.getAuthorId())) {
            throw new InvalidBasicAuthorizationException("You are not authorized");
        }
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseValue> updateProject(@PathVariable int id, @RequestBody Project project,
                                                 @RequestHeader String authorization)
            throws ChangeSetPersister.NotFoundException, InvalidBasicAuthorizationException {
        UserEntity userEntity = userEntityService.getUserEntity(authorization);
        if (userEntity == null) {
            throw new InvalidBasicAuthorizationException("You are not authorized to view this project");
        }
        if (project.getAuthorId().equals(userEntity.getId())) {
            project.setId(id);
            return ResponseEntity.ok(projectService.updateProject(project));
        }
        throw new InvalidBasicAuthorizationException("You are not owner of this project");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id,
                                              @RequestHeader String authorization)
            throws InvalidBasicAuthorizationException, ChangeSetPersister.NotFoundException {
        UserEntity userEntity = userEntityService.getUserEntity(authorization);
        if (userEntity == null) {
            throw new InvalidBasicAuthorizationException("You are not authorized to delete this project");
        }
        Project project = projectService.getProjectById(id);
        if (project.getAuthorId().equals(userEntity.getId())) {
            projectService.deleteProject(id);
            return ResponseEntity.ok().build();
        }
        throw new InvalidBasicAuthorizationException("You are not owner of this project and cannot delete it");
    }
}

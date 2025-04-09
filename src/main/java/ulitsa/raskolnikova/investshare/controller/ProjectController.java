package ulitsa.raskolnikova.investshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ulitsa.raskolnikova.investshare.dto.Project;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;
import ulitsa.raskolnikova.investshare.service.ProjectService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project)
            throws ChangeSetPersister.NotFoundException {
        project.setId(id);
        return ResponseEntity.ok(projectService.updateProject(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}

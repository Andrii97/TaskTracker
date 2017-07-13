package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.model.dto.ProjectTeamDTO;
import ua.model.entity.Project;
import ua.model.entity.ProjectTeam;
import ua.model.exception.NotFoundException;
import ua.model.service.ProjectService;

import java.util.List;

/**
 * @author Andrii Severin
 */
@RepositoryRestController
public class ProjectRestController {
    private final ProjectService projectService;

    @Autowired
    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "/projects/{id}")
    @ResponseBody
    public ResponseEntity<Project> getProject(@PathVariable long id) {
        Project response = projectService.getById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/projects/{id}/projectTeam")
    @ResponseBody
    public ResponseEntity<List<ProjectTeam>> getProjectTeam(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectTeamByProjectId(id));
    }

    @GetMapping(path = "/projects/{id}/projectTeam/{userId}")
    @ResponseBody
    public ResponseEntity<ProjectTeam> getProjectTeamByUserId(@PathVariable long id,
                                                              @PathVariable long userId) {
        ProjectTeam response = projectService.getProjectTeamByProjectIdAndUserId(id, userId)
                .orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/projects/{id}/projectTeam")
    @ResponseBody
    public ResponseEntity<ProjectTeam> addToProjectTeam(@PathVariable long id,
                                                        @RequestBody ProjectTeamDTO projectTeamDTO) {
        ProjectTeam response = projectService.addToProjectTeam(id, projectTeamDTO);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/projects/{id}/projectTeam/{userId}")
    @ResponseBody
    public ResponseEntity<ProjectTeam> updateProjectTeam(@PathVariable long id,
                                           @PathVariable long userId,
                                           @RequestBody ProjectTeamDTO projectTeamDTO) {
        ProjectTeam response = projectService.updateProjectTeam(id, userId, projectTeamDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/projects/{id}/projectTeam/{userId}")
    @ResponseBody
    public ResponseEntity<ProjectTeam> deleteFromProjectTeam(@PathVariable long id,
                                                         @PathVariable long userId) {
        boolean result = projectService.deleteFromProjectTeam(id, userId);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}

package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.controller.dto.ProjectTeamDTO;
import ua.model.entity.Project;
import ua.model.entity.ProjectTeam;
import ua.model.entity.ProjectTeamId;
import ua.model.entity.User;
import ua.model.repository.ProjectRepository;
import ua.model.repository.ProjectTeamRepository;
import ua.model.repository.UserRepository;

import java.util.List;

@RepositoryRestController
public class ProjectRestController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectTeamRepository projectTeamRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/projects/{id}")
    @ResponseBody
    public ResponseEntity<Project> getProject(@AuthenticationPrincipal User user,
                                              @PathVariable Long id) {
        System.out.println(user.toString());
        Project project = projectRepository.findOne(id);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(path = "/projects/{id}/projectTeam")
    @ResponseBody
    public ResponseEntity<List<ProjectTeam>> getProjectTeam(Authentication authentication,
            @PathVariable Long id) {
        System.out.println(authentication);
        return new ResponseEntity<>(projectTeamRepository.findByProjectId(id), HttpStatus.OK);
    }

    @GetMapping(path = "/projects/{id}/projectTeam/{userId}")
    @ResponseBody
    public ResponseEntity<ProjectTeam> getProjectTeamByUserId(@PathVariable Long id,
                                                  @PathVariable Long userId) {
        ProjectTeam projectTeam = projectTeamRepository.findByProjectIdAndUserId(id, userId);
        if (projectTeam == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(projectTeam, HttpStatus.OK);
    }

    @PostMapping(path = "/projects/{id}/projectTeam")
    @ResponseBody
    public ResponseEntity<ProjectTeam> addToProjectTeam(@PathVariable Long id,
                                                        @RequestBody ProjectTeamDTO projectTeamDTO) {
        ProjectTeam projectTeam = new ProjectTeam();
        projectTeam.setIsConfirmed(projectTeamDTO.getConfirmed());
        projectTeam.setRole(projectTeamDTO.getRole());
        projectTeam.setProject(projectRepository.findOne(id));
        projectTeam.setUser(userRepository.findOne(projectTeamDTO.getUserId()));

        return new ResponseEntity<>(projectTeamRepository.save(projectTeam), HttpStatus.OK);
    }


    @PutMapping("/projects/{id}/projectTeam/{userId}")
    @ResponseBody
    public ResponseEntity<ProjectTeam> updateProjectTeam(@PathVariable Long id,
                                           @PathVariable Long userId,
                                           @RequestBody ProjectTeamDTO projectTeamDTO) {
        ProjectTeam oldProjectTeam = projectTeamRepository.findByProjectIdAndUserId(id, userId);
        if (oldProjectTeam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldProjectTeam.setIsConfirmed(projectTeamDTO.getConfirmed());
        oldProjectTeam.setRole(projectTeamDTO.getRole());
        oldProjectTeam.setProject(projectRepository.findOne(id));
        oldProjectTeam.setUser(userRepository.findOne(userId));

        return new ResponseEntity<>(projectTeamRepository.save(oldProjectTeam), HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}/projectTeam/{userId}")
    @ResponseBody
    public ResponseEntity<ProjectTeam> updateProjectTeam(@PathVariable Long id,
                                                         @PathVariable Long userId) {
        projectTeamRepository.delete(new ProjectTeamId(id, userId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

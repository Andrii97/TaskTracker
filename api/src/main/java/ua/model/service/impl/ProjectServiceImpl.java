package ua.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.model.dto.ProjectTeamDTO;
import ua.model.entity.Project;
import ua.model.entity.ProjectTeam;
import ua.model.entity.ProjectTeamId;
import ua.model.entity.User;
import ua.model.exception.NotFoundException;
import ua.model.repository.ProjectRepository;
import ua.model.repository.ProjectTeamRepository;
import ua.model.repository.UserRepository;
import ua.model.service.ProjectService;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0 11 Jul 2017
 * @author Andrii Severin
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectTeamRepository projectTeamRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              ProjectTeamRepository projectTeamRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectTeamRepository = projectTeamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectTeam addToProjectTeam(long projectId, ProjectTeamDTO projectTeamDTO) {
        Project project = projectRepository.findOne(projectId).orElseThrow(NotFoundException::new);
        User user = userRepository.findOne(projectTeamDTO.getUserId()).orElseThrow(NotFoundException::new);

        ProjectTeam projectTeam = ProjectTeam.builder()
                .pk(new ProjectTeamId(projectId, user.getId()))
                .isConfirmed(projectTeamDTO.isConfirmed())
                .role(projectTeamDTO.getRole())
                .project(project)
                .user(user)
                .build();

        return projectTeamRepository.save(projectTeam);
    }

    @Override
    public boolean deleteFromProjectTeam(long projectId, long userId) {
        projectTeamRepository.delete(new ProjectTeamId(projectId, userId));
        return true;
    }

    @Override
    public ProjectTeam updateProjectTeam(long projectId, long userId, ProjectTeamDTO projectTeamDTO) {
        User user = userRepository.findOne(projectTeamDTO.getUserId()).orElseThrow(NotFoundException::new);
        ProjectTeam projectTeam = projectTeamRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(NotFoundException::new);

        projectTeam.setIsConfirmed(projectTeamDTO.isConfirmed());
        projectTeam.setRole(projectTeamDTO.getRole());
        projectTeam.setUser(user);

        return projectTeamRepository.save(projectTeam);
    }

    @Override
    public Optional<Project> getById(long projectId) {
        return projectRepository.findOne(projectId);
    }

    @Override
    public Optional<ProjectTeam> getProjectTeamByProjectIdAndUserId(long projectId, long userId) {
        return projectTeamRepository.findByProjectIdAndUserId(projectId, userId);
    }

    @Override
    public List<ProjectTeam> getProjectTeamByProjectId(long projectId) {
        Project project = projectRepository.findOne(projectId).orElseThrow(NotFoundException::new);
        return projectTeamRepository.findByProject(project);
    }
}

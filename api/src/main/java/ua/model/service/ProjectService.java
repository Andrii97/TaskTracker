package ua.model.service;

import ua.model.dto.ProjectTeamDTO;
import ua.model.entity.Project;
import ua.model.entity.ProjectTeam;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0 11 Jul 2017
 * @author Andrii Severin
 */
public interface ProjectService {
    Optional<Project> getById(long projectId);
    Optional<ProjectTeam> getProjectTeamByProjectIdAndUserId(long projectId, long userId);
    List<ProjectTeam> getProjectTeamByProjectId(long projectId);
    ProjectTeam addToProjectTeam(long projectId, ProjectTeamDTO projectTeamDTO);
    boolean deleteFromProjectTeam(long projectId, long userId);
    ProjectTeam updateProjectTeam(long projectId, long userId, ProjectTeamDTO projectTeamDTO);
}

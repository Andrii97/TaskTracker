package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.model.entity.Project;
import ua.model.entity.ProjectTeam;
import ua.model.entity.ProjectTeamId;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Severin
 */
@RepositoryRestResource(exported = false)
public interface ProjectTeamRepository extends PagingAndSortingRepository<ProjectTeam, ProjectTeamId> {
    List<ProjectTeam> findByUserId(long userId);
    List<ProjectTeam> findByProject(Project project);
    Optional<ProjectTeam> findByProjectIdAndUserId(long projectId, long userId);
}

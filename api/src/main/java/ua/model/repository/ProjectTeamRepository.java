package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.model.entity.ProjectTeam;
import ua.model.entity.ProjectTeamId;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ProjectTeamRepository extends PagingAndSortingRepository<ProjectTeam, ProjectTeamId> {
    List<ProjectTeam> findByUserId(Long userId);
    List<ProjectTeam> findByProjectId(Long projectId);
    ProjectTeam findByProjectIdAndUserId(Long projectId, Long userId);
}

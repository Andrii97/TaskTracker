package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.model.entity.Project;

@RepositoryRestResource
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
}

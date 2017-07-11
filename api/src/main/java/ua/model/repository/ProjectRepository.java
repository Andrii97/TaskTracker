package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.model.entity.Project;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    Optional<Project> findOne(long id);
}

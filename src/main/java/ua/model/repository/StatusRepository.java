package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.model.entity.Status;

@RepositoryRestResource
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
}
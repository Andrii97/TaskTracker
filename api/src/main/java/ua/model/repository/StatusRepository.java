package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.model.entity.Status;

@RepositoryRestResource
@CrossOrigin
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
}
package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.model.entity.Status;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
    Optional<Status> findOne(long id);
}
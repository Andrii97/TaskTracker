package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.model.entity.User;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @RestResource(path = "email", rel = "email")
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findOne(long id);
}
package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ua.model.entity.User;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @RestResource(path = "email", rel = "email")
    User findByEmail(@Param("email") String email);
}

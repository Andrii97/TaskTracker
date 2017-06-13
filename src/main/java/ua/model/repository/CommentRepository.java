package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ua.model.entity.Comment;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    @RestResource(path = "taskId", rel = "taskId")
    List<Comment> findByTaskId(@Param("taskId") Long taskId);
}
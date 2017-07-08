package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.model.entity.Comment;

import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    @RestResource(path = "taskId", rel = "taskId")
    List<Comment> findByTaskId(@Param("taskId") Long taskId);
}
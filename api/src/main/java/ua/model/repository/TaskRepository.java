package ua.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.model.entity.Task;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
@CrossOrigin
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    @RestResource(path = "userId", rel = "userId")
    List<Task> findByUserId(@Param("userId") Long userId);
    @RestResource(path = "userIdAndStatusId", rel = "userIdAndStatusId")
    List<Task> findByUserIdAndStatusId(@Param("userId") Long userId, @Param("statusId") Long statusId);
    @RestResource(path = "deadlineAtAfter", rel = "deadlineAtAfter")
    List<Task> findByDeadlineAtAfter(@Param("deadlineAt") LocalDate deadlineAt);
    @RestResource(path = "deadlineAtBefore", rel = "deadlineAtBefore")
    List<Task> findByDeadlineAtBefore(@Param("deadlineAt") LocalDate deadlineAt);
}

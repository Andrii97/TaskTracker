package ua.model.service;

import ua.model.dto.TaskDTO;
import ua.model.entity.Task;

import java.util.Optional;

/**
 * @version 1.0 12 Jul 2017
 * @author Andrii Severin
 */
public interface TaskService {
    Optional<Task> getTaskById(long id);
    Task createTask(TaskDTO taskDTO);
    Task updateTask(long id, TaskDTO taskDTO);
}

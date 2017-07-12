package ua.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.model.dto.TaskDTO;
import ua.model.entity.Project;
import ua.model.entity.Status;
import ua.model.entity.Task;
import ua.model.entity.User;
import ua.model.exception.NotFoundException;
import ua.model.repository.ProjectRepository;
import ua.model.repository.StatusRepository;
import ua.model.repository.TaskRepository;
import ua.model.repository.UserRepository;
import ua.model.service.TaskService;

import java.util.Optional;

/**
 * @version 1.0 12 Jul 2017
 * @author Andrii Severin
 */
@Service
public class TaskServiceImpl implements TaskService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public TaskServiceImpl(ProjectRepository projectRepository, UserRepository userRepository,
                           TaskRepository taskRepository, StatusRepository statusRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Optional<Task> getTaskById(long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task createTask(TaskDTO taskDTO) {
        User user = userRepository.findOne(taskDTO.getUserId()).orElseThrow(NotFoundException::new);
        Project project = projectRepository.findOne(taskDTO.getProjectId()).orElseThrow(NotFoundException::new);
        Status status = statusRepository.findOne(taskDTO.getStatusId()).orElseThrow(NotFoundException::new);

        Task task = Task.builder()
                .name(taskDTO.getName())
                .deadlineAt(taskDTO.getDeadlineAt())
                .description(taskDTO.getDescription())
                .parent(taskRepository.findOne((Long) taskDTO.getParentId()))
                .user(user)
                .project(project)
                .status(status)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(long id, TaskDTO taskDTO) {
        Task task = taskRepository.findOne(id).orElseThrow(NotFoundException::new);
        Status status = statusRepository.findOne(taskDTO.getStatusId()).orElseThrow(NotFoundException::new);
        User user = userRepository.findOne(taskDTO.getUserId()).orElseThrow(NotFoundException::new);

        task.setName(taskDTO.getName());
        task.setDeadlineAt(taskDTO.getDeadlineAt());
        task.setDescription(taskDTO.getDescription());
        task.setParent(taskRepository.findOne((Long) taskDTO.getParentId()));
        task.setUser(user);
        task.setStatus(status);

        return taskRepository.save(task);
    }
}

package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.controller.dto.TaskDTO;
import ua.model.entity.Task;
import ua.model.repository.ProjectRepository;
import ua.model.repository.StatusRepository;
import ua.model.repository.TaskRepository;
import ua.model.repository.UserRepository;

@RepositoryRestController
public class TaskRestController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StatusRepository statusRepository;

    @GetMapping(path = "/tasks/{id}")
    @ResponseBody
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping(path = "/tasks")
    @ResponseBody
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = getTaskFromDTO(new Task(), taskDTO);
        return new ResponseEntity<>(taskRepository.save(task), HttpStatus.CREATED);
    }

    @PutMapping(path = "/tasks/{id}")
    @ResponseBody
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task oldTask = taskRepository.findOne(id);
        if (oldTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Task task = getTaskFromDTO(oldTask, taskDTO);
        // task.setId(id);
        return new ResponseEntity<>(taskRepository.save(task), HttpStatus.OK);
    }

    private Task getTaskFromDTO(Task newTask, TaskDTO taskDTO) {
        newTask.setName(taskDTO.getName());
        newTask.setDeadlineAt(taskDTO.getDeadlineAt());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setParent(taskRepository.findOne(taskDTO.getParentId()));
        newTask.setUser(userRepository.findOne(taskDTO.getUserId()));
        newTask.setProject(projectRepository.findOne(taskDTO.getProjectId()));
        newTask.setStatus(statusRepository.findOne(taskDTO.getStatusId()));
        return newTask;
    }

}

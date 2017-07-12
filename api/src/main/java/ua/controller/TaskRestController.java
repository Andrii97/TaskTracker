package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.model.dto.TaskDTO;
import ua.model.entity.Task;
import ua.model.exception.NotFoundException;
import ua.model.service.TaskService;

/**
 * @author Andrii Severin
 */
@RepositoryRestController
public class TaskRestController {
    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/tasks/{id}")
    @ResponseBody
    public ResponseEntity<Task> getTask(@PathVariable long id) {
        Task response = taskService.getTaskById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/tasks")
    @ResponseBody
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task response = taskService.createTask(taskDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/tasks/{id}")
    @ResponseBody
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        Task response = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(response);
    }
}

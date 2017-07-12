package ua.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.model.dto.TaskDTO;
import ua.model.entity.Task;
import ua.model.exception.NotFoundException;
import ua.model.service.TaskService;

/**
 * @version 1.0 12 Jun 2017
 * @author Andrii Severin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    @Sql(scripts = "classpath:data/data.sql")
    public void createTask() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-1)
                .projectId(-1)
                .statusId(-1)
                .build();
        Task response = taskService.createTask(taskDTO);
        assert (response != null);
        assert (response.getUser().getId() == -1);
        assert (response.getProject().getId() == -1);
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void createTaskWithUnknownProject() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-1)
                .projectId(-100)
                .statusId(-1)
                .build();
        taskService.createTask(taskDTO);
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void createTaskWithUnknownStatus() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-1)
                .projectId(-1)
                .statusId(-100)
                .build();
        taskService.createTask(taskDTO);
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void createTaskWithUnknownUser() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-100)
                .projectId(-1)
                .statusId(-1)
                .build();
        taskService.createTask(taskDTO);
    }

    @Test
    @Sql(scripts = "classpath:data/data.sql")
    public void updateTask() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-2)
                .statusId(-2)
                .build();
        Task response = taskService.updateTask(-2, taskDTO);
        assert (response != null);
        assert (response.getUser().getId() == -2);
        assert (response.getStatus().getId() == -2);
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void updateTaskWithUnknownUser() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-200)
                .statusId(-2)
                .build();
        taskService.updateTask(-2, taskDTO);
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void updateTaskWithUnknownStatus() throws Exception {
        TaskDTO taskDTO = TaskDTO.builder()
                .name("New task")
                .userId(-2)
                .statusId(-200)
                .build();
        taskService.updateTask(-2, taskDTO);
    }
}

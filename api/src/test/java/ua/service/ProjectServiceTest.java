package ua.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.model.dto.ProjectTeamDTO;
import ua.model.entity.ProjectTeam;
import ua.model.entity.Role;
import ua.model.exception.NotFoundException;
import ua.model.service.ProjectService;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0 12 Jun 2017
 * @author Andrii Severin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    @Sql(scripts = "classpath:data/data.sql")
    public void addToProjectTeam() throws Exception {
        ProjectTeamDTO projectTeamDTO = ProjectTeamDTO.builder()
                .isConfirmed(true)
                .role(Role.MANAGER)
                .userId(-2)
                .build();
        ProjectTeam response = projectService.addToProjectTeam(-1, projectTeamDTO);
        assert (response != null);
        assert (response.getUser().getId() == -2);
    }

    @Test
    @Sql(scripts = "classpath:data/data.sql")
    public void deleteFromProjectTeam() throws Exception {
        projectService.deleteFromProjectTeam(-1, -1);
        Optional<ProjectTeam> projectTeam = projectService.getProjectTeamByProjectIdAndUserId(-1, -1);
        assert (!projectTeam.isPresent());
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void addUnknownUserToProjectTeam() throws Exception {
        projectService.addToProjectTeam(-1, ProjectTeamDTO.builder().userId(-100).build());
    }

    @Test
    @Sql(scripts = "classpath:data/data.sql")
    public void getProjectTeamByProjectId() throws Exception {
        List<ProjectTeam> projectTeamList = projectService.getProjectTeamByProjectId(-2);
        assert (projectTeamList.size() == 2);
    }

    @Test(expected = NotFoundException.class)
    @Sql(scripts = "classpath:data/data.sql")
    public void getProjectTeamByUnknownProjectId() throws Exception {
        projectService.getProjectTeamByProjectId(-100);
    }
}

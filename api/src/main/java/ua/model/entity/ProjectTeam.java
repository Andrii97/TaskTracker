package ua.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Andrii Severin
 */
@Entity
@Table(name = "user_has_project")
@Data
public class ProjectTeam implements Serializable {
    @EmbeddedId
    private ProjectTeamId pk = new ProjectTeamId();

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate addedAt;

    @NotNull
    private Role role;

    private Boolean isConfirmed;

    @PrePersist
    protected void onCreate() {
        addedAt = LocalDate.now();
    }
}

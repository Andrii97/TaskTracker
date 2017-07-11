package ua.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Andrii Severin
 */
@Entity
@Data
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String description;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<ProjectTeam> projectTeam;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<Task> tasks;

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}

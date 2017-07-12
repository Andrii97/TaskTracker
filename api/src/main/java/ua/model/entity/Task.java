package ua.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Andrii Severin
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Task parent;

    @NotNull
    private String name;
    private String description;
    private LocalDate createdAt;
    private Date deadlineAt;

    @JoinColumn(name = "project_id ")
    @NotNull
    @ManyToOne
    @JsonBackReference
    private Project project;

    @NotNull
    @ManyToOne
    private Status status;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}

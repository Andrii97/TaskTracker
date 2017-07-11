package ua.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Andrii Severin
 */
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String body;

    private LocalDateTime sentAt;

    @NotNull
    @ManyToOne
    private User sender;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Task task;

    @PrePersist
    protected void onCreate() {
        sentAt = LocalDateTime.now();
    }
}

package ua.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_has_project")
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
    private Role role;
    private Boolean isConfirmed;

    public ProjectTeamId getPk() {
        return pk;
    }

    public void setPk(ProjectTeamId pk) {
        this.pk = pk;
    }

    @PrePersist
    protected void onCreate() {
        addedAt = LocalDate.now();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }


    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

//    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectTeam that = (ProjectTeam) o;

        if (!pk.equals(that.pk)) return false;
        if (addedAt != null ? !addedAt.equals(that.addedAt) : that.addedAt != null) return false;
        if (role != that.role) return false;
        return isConfirmed != null ? isConfirmed.equals(that.isConfirmed) : that.isConfirmed == null;
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        result = 31 * result + (addedAt != null ? addedAt.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isConfirmed != null ? isConfirmed.hashCode() : 0);
        return result;
    }
}

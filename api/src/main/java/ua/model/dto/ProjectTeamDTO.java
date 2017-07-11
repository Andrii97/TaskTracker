package ua.model.dto;

import lombok.Builder;
import lombok.Data;
import ua.model.entity.Role;

/**
 * @author Andrii Severin
 */
@Data
@Builder
public class ProjectTeamDTO {
    private long userId;
    private Role role;
    private boolean isConfirmed;
}

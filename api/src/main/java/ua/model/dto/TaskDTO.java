package ua.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

/**
 * @author Andrii Severin
 */
@Data
@Builder
public class TaskDTO {
    private String name;
    private String description;
    private long projectId;
    private long userId;
    private long statusId;
    private Date deadlineAt;
    private long parentId;
}

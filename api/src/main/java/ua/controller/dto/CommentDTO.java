package ua.controller.dto;


public class CommentDTO {
    private String body;
    private Long senderId;
    private Long taskId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "body='" + body + '\'' +
                ", senderId=" + senderId +
                ", taskId=" + taskId +
                '}';
    }
}

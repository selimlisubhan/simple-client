package interview.client.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class MessageModel implements Serializable {
    private static final long serialVersionUID = 442234611796506796L;

    private int id;
    private String message;
    private int status;
    private NotificationStatus notificationStatus;
    protected LocalDateTime insertDate;
    protected LocalDateTime processDate;

    public MessageModel(int id, String message, int status, NotificationStatus notificationStatus) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.notificationStatus = notificationStatus;
        insertDate = LocalDateTime.now();
        processDate = null;
    }

    public MessageModel() {
        this.id = 0;
        this.message = "";
        this.status = 1;
        this.notificationStatus = NotificationStatus.PENDING;
        insertDate = LocalDateTime.now();
        processDate = null;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", notificationStatus=" + notificationStatus +
                ", insertDate=" + insertDate +
                ", processDate=" + processDate +
                '}';
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

}

package interview.client.model;

public enum NotificationStatus {

    PENDING(0), PROCESSING(1), SUCCESS(2), ERROR(3);

    private int value;

    NotificationStatus(int value) {
        this.value = value;
    }

    public static NotificationStatus fromValue(int value) {
        NotificationStatus status = null;

        if(value == 0) {
            status = PENDING;
        } else if(value == 1) {
            status = PROCESSING;
        } else if(value == 2) {
            status = SUCCESS;
        } else if(value == 3) {
            status = ERROR;
        }

        return status;
    }

    public int getValue() {
        return value;
    }
}

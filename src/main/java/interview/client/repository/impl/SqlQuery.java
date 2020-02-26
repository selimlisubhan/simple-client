package interview.client.repository.impl;

public class SqlQuery {

    public static final String GET_PENDING_NOTIFICATIONS = "select id, message, notification_status, status from TBL_MESSAGES where notification_status = 0 " +
            "order by id";

    public static final String UPDATE_NOTIFICATION = "update TBL_MESSAGES " +
            "set status = ? " +
            "where id = ?";

}
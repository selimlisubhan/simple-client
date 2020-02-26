package interview.client.repository;

import interview.client.model.MessageModel;

import java.util.Optional;

public interface NotificationRepository {
    Optional<MessageModel> getPendingNotifications();
    void updateNotification(MessageModel notification);
}

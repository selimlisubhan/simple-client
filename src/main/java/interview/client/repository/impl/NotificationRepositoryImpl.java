package interview.client.repository.impl;

import interview.client.model.MessageModel;
import interview.client.repository.NotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {


    private List<MessageModel> messageModels;


    public NotificationRepositoryImpl(List<MessageModel> messageModels) {
        this.messageModels = messageModels;
    }

    @Override
    public Optional<MessageModel> getPendingNotifications() {
        return messageModels.stream()
                .filter(n -> n.getNotificationStatus().getValue() < 1)
                .findFirst();
    }

    @Override
    public void updateNotification(MessageModel notification) {
        Object[] data = {notification.getStatus(), notification.getNotificationStatus().getValue(), notification.getId()};
        messageModels.set(notification.getId() - 1, notification);
        System.out.println("update message : " + notification);
        System.out.println("last messageList : " + messageModels);
    }

}

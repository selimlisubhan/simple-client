package interview.client;

import interview.client.model.MessageModel;
import interview.client.model.NotificationStatus;
import interview.client.repository.NotificationRepository;
import interview.client.repository.impl.NotificationRepositoryImpl;
import interview.client.service.NotificationTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientApplication {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService checkService = Executors.newScheduledThreadPool(cores);
        ExecutorService senderService = Executors.newSingleThreadExecutor();
        NotificationRepository repository = new NotificationRepositoryImpl(getModel(100));

        checkService.scheduleAtFixedRate(() -> {
            Optional<MessageModel> notificationOptional = repository.getPendingNotifications();

            if(notificationOptional.isPresent()){
                MessageModel notification = notificationOptional.get();
                notification.setNotificationStatus(NotificationStatus.PROCESSING);
                notification.setProcessDate(LocalDateTime.now());
                repository.updateNotification(notification);
                NotificationTask task = new NotificationTask(repository, notification);
                senderService.submit(task);
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    private static List<MessageModel> getModel(int count){
        List<MessageModel> messageModels = new ArrayList<>();
        for (int i = 1; i < count; i++){
            messageModels.add(new MessageModel(i,"message"+i, 0, NotificationStatus.PENDING));
        }
        return messageModels;
    }

}

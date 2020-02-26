//package interview.client.sender;
//
//import interview.client.model.MessageModel;
//import interview.client.repository.NotificationRepository;
//import interview.client.service.NotificationTask;
//import interview.client.utils.CustomHttpClient;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.http.client.methods.HttpGet;
//import org.json.JSONObject;
//
//public class NotificationSender {
//
//
//    public void send() {
//
//        try {
//            int cores = Runtime.getRuntime().availableProcessors();
//            ScheduledExecutorService checkService = Executors.newScheduledThreadPool(cores);
//            ExecutorService senderService = Executors.newFixedThreadPool(cores);
//
//            checkService.scheduleAtFixedRate(() -> {
//                List<MessageModel> notificationList = repository.getPendingNotifications(1);
//                MessageModel notification = notificationList.get(0);
//                System.out.println(LocalDateTime.now() + " pending notifications = " + notificationList.size());
//
//                HttpGet getRequest = new HttpGet("http://localhost:8080/notify/getnotify?id=" + notification.getId() + "&message=" + notification.getMessage());
//                HttpResponse response = null;
//                try {
//                    response = CustomHttpClient.getHttpClient().execute(getRequest);
//
//                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                        String json = EntityUtils.toString(response.getEntity());
//
//                        JSONObject jsonObject = null;
//                        try {
//                            jsonObject = new JSONObject(json);
//                            notification.setId(jsonObject.getInt("id"));
//                            notification.setMessage(jsonObject.getString("message"));
//                            notification.setStatus(jsonObject.getInt("status"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    notification.setStatus(1);
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//                notificationList.forEach(notify -> {
//                    NotificationTask task = new NotificationTask(repository);
//                    senderService.submit(task);
//                });
//            }, 0, 20, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

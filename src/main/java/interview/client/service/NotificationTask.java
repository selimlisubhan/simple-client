package interview.client.service;

import interview.client.model.MessageModel;
import interview.client.model.NotificationStatus;
import interview.client.repository.NotificationRepository;
import interview.client.utils.CustomHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NotificationTask implements Runnable {


    private static final Logger log = LoggerFactory.getLogger(NotificationTask.class);

    private NotificationRepository repository;

    private MessageModel model;

    public NotificationTask(NotificationRepository repository, MessageModel model) {
        this.repository = repository;
        this.model = model;
    }

    @Override
    public void run() {

        HttpGet getRequest = new HttpGet("http://localhost:8080/sms/send?id=" + model.getId() + "&message=" + model.getMessage());
        HttpResponse response = null;
        try {
            response = CustomHttpClient.getHttpClient().execute(getRequest);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String json = EntityUtils.toString(response.getEntity());
                System.out.println("response json data : " + json);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(json);
                    model.setStatus(jsonObject.getInt("status"));
                    model.setNotificationStatus(NotificationStatus.SUCCESS);
                } catch (JSONException e) {
                    model.setStatus(0);
                    model.setNotificationStatus(NotificationStatus.ERROR);
                    e.printStackTrace();
                } finally {
                    repository.updateNotification(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package singleton;

import java.util.*;
import model.Message;
import notification.Notification;
import observer.NotificationObserver;

public class NotificationManager {

    private static NotificationManager instance;
    private List<NotificationObserver> observers = new ArrayList<>();

    private NotificationManager() {}

    public static synchronized NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    public void registerObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void sendNotification(Notification notification, Message message) {
        notification.send(message);
        notifyObservers(message.getContent());
    }

    private void notifyObservers(String content) {
        for (NotificationObserver o : observers) {
            o.update(content);
        }
    }
}
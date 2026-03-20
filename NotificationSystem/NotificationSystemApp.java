import decorator.*;
import factory.NotificationFactory;
import model.Message;
import notification.Notification;
import observer.*;
import singleton.NotificationManager;
import strategy.ImmediateSendStrategy;

public class NotificationSystemApp {

    public static void main(String[] args) {

        Message base = () -> "Hello User!";
        Message encrypted = new EncryptedMessage(base);
        Message decorated = new TimestampedMessage(encrypted);

        Notification notification = NotificationFactory.createNotification("email");
        notification.setStrategy(new ImmediateSendStrategy());

        NotificationManager manager = NotificationManager.getInstance();
        manager.registerObserver(new LoggerObserver());
        manager.registerObserver(new AnalyticsObserver());

        manager.sendNotification(notification, decorated);
    }
}
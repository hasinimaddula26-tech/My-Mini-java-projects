package observer;

public class AnalyticsObserver implements NotificationObserver {

    public void update(String messageContent) {
        System.out.println("[Analytics] Tracked: " + messageContent);
    }
}
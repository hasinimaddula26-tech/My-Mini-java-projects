package notification;

import model.Message;
import strategy.SendStrategy;

public abstract class Notification {

    protected SendStrategy strategy;

    public void setStrategy(SendStrategy strategy) {
        this.strategy = strategy;
    }

    public void send(Message message) {
        if (strategy != null) {
            strategy.send(message);
        } else {
            System.out.println("No strategy defined.");
        }
    }
}
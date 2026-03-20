package decorator;

import model.Message;

public abstract class NotificationDecorator implements Message {
    protected Message message;

    public NotificationDecorator(Message message) {
        this.message = message;
    }
}
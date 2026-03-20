package decorator;

import model.Message;
import java.time.LocalDateTime;

public class TimestampedMessage extends NotificationDecorator {

    public TimestampedMessage(Message message) {
        super(message);
    }

    public String getContent() {
        return message.getContent() + " @ " + LocalDateTime.now();
    }
}
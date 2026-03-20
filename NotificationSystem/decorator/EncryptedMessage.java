package decorator;

import model.Message;

public class EncryptedMessage extends NotificationDecorator {

    public EncryptedMessage(Message message) {
        super(message);
    }

    public String getContent() {
        return "[Encrypted] " + message.getContent();
    }
}
package strategy;

import model.Message;

public class ImmediateSendStrategy implements SendStrategy {

    public void send(Message message) {
        System.out.println("Sending immediately: " + message.getContent());
    }
}

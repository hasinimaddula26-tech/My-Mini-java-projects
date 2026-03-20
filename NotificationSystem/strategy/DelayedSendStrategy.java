package strategy;

import model.Message;

public class DelayedSendStrategy implements SendStrategy {

    public void send(Message message) {
        System.out.println("Sending after delay: " + message.getContent());
    }
}
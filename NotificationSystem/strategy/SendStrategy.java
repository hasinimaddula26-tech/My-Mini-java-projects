package strategy;

import model.Message;

public interface SendStrategy {
    void send(Message message);
}
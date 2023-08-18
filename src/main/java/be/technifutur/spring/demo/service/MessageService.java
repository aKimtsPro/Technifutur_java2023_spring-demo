package be.technifutur.spring.demo.service;

import java.util.List;

public interface MessageService {
    
    String getLastMessage();
    List<String> getMessages();
    String getMessage(int index);
    void addMessage(String toAdd);
    void deleteMessage(int index);
    void changeMessage(int index, String replacemnt);

}

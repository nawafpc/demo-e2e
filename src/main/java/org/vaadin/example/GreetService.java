package org.vaadin.example;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaadin.flow.component.messages.MessageListItem;

@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }
    private final List<MessageListItem> messageList = new ArrayList<>();

    public void addMessage(String answer, String userName) {
        MessageListItem newMessage = new MessageListItem(
                answer,
                LocalDateTime.now().toInstant(ZoneOffset.UTC),
                userName // Use the actual user's name
        );
        newMessage.setUserColorIndex(3); // Set an appropriate index for color
        messageList.add(newMessage);
    }

    public List<MessageListItem> getMessages() {
        return messageList;
    }
}
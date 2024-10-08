package org.vaadin.example;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    private final GreetService messageService;
    public MainView(@Autowired GreetService messageService) {

        this.messageService = messageService;

        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1("E2E Community");
        Hr hr = new Hr();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3("Question ?");
        MessageInput messageInput = new MessageInput();
        Hr hr2 = new Hr();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H3 h32 = new H3();
        MessageInput messageInput2 = new MessageInput();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        MessageList messageList = new MessageList();
        Hr hr3 = new Hr();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary = new Button("Evaluation");
        Button buttonPrimary2 = new Button("Coach Evaluate");

        // Set layout properties
        layoutRow.setWidthFull();
        layoutRow.add(h1);
        layoutColumn2.add(h3, messageInput, hr2);
        layoutColumn3.add(h32, messageInput2);
        layoutColumn4.add(messageList, hr3);
        layoutRow2.add(buttonPrimary, buttonPrimary2);

        add(layoutRow, hr, layoutColumn2, layoutColumn3, layoutColumn4, layoutRow2);

        // Add submit listeners for inputs
        messageInput.addSubmitListener(event -> {
            String messageText = event.getValue();
            h32.setText(messageText);
            messageInput.getElement().executeJs("this.value = '';"); // Clear input
        });

        messageInput2.addSubmitListener(event -> {
            String messageText = event.getValue();
            messageService.addMessage(messageText, "Your Name"); // Add message via service
            addMessagesToList(messageList); // Update message list display
            messageInput2.getElement().executeJs("this.value = '';"); // Clear input
        });
    }

    private void addMessagesToList(MessageList messageList) {
        List<MessageListItem> currentMessages = messageService.getMessages();
        messageList.setItems(currentMessages);
    }
    }
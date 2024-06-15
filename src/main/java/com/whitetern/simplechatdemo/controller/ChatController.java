package com.whitetern.simplechatdemo.controller;

import com.whitetern.simplechatdemo.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessage message) {
        String content = HtmlUtils.htmlEscape(message.getContent());
        String sender = HtmlUtils.htmlEscape(message.getSender());
        String receiver = HtmlUtils.htmlEscape(message.getReceiver());

        ChatMessage msg = new ChatMessage();
        msg.setContent(content);
        msg.setSender(sender);
        msg.setReceiver(receiver);

        messagingTemplate.convertAndSendToUser(receiver, "/queue/messages", msg);
    }
}

package com.whitetern.simplechatdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessage {

    private String content;
    private String sender;
    private String receiver;
}

package com.liargame.liar.chap02.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ChatDTO {
    private String roomId;
    private String writer;
    private String message;
}

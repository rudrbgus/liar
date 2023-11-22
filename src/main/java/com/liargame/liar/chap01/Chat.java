package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Chat {
    private String userName;
    private String userContext;

    public Chat(String userName, String userContext) {
        this.userName = userName;
        this.userContext = userContext;
    }
}

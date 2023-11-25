package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameChat {
    private String userName;
    private String userContext;

    public GameChat(String userName, String userContext) {
        this.userName = userName;
        this.userContext = userContext;
    }
}

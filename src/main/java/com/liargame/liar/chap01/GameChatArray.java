package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameChatArray {
    public static List<GameChat> gameChatList;
    static {
        gameChatList = new ArrayList<>();
    }
}

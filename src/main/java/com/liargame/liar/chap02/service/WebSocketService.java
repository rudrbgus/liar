package com.liargame.liar.chap02.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Service
public class WebSocketService {

    // 1이면 유저가 방에 들어온거임 -> 유저 리스트에 추가하고 다시 유저 리스트를 사용자들에게 보내줌

    public void get(String payload, List<WebSocketSession> session) throws IOException {
        if (payload.equals("1")){
            for (WebSocketSession ws : session) {
                ws.sendMessage(new TextMessage("1"));
            }
        }


    }
}

package com.liargame.liar.chap02.handler;

import com.liargame.liar.chap02.service.LiarGameService;
import com.liargame.liar.chap02.service.WebSocketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> userList =new ArrayList<>();

    public MyWebSocketHandler() {

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트 연결 성공 시 동작
        log.info("연결 성공");
        session.sendMessage(new TextMessage("서버로부터의 환영 메시지!"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 텍스트 메시지 수신 시 동작
        String payload = message.getPayload();
        log.info("Received message: " + payload);
        userList.add(session);
        if (payload.equals("1")){
            for (WebSocketSession ws : userList) {
                ws.sendMessage(new TextMessage("1"));
            }
        }


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 클라이언트 연결 종료 시 동작
        log.info("WebSocket 연결 종료");
        session.close();
    }
}
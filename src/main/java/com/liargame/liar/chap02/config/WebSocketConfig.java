package com.liargame.liar.chap02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // 간단한 메모리 기반 메시지 브로커 활성화 클라이언트가 /topic을 구독 하면 서버가 클라이언트로 메세지를 보낼 수 있음
        config.setApplicationDestinationPrefixes("/app"); // 클라이언트가 서버로 /app 접두어를 붙여서 보낼 수 있음
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000").withSockJS(); // WebSocket을 지원하지 않는 클라이언트를 위한 SockJS 폴백
    }
}
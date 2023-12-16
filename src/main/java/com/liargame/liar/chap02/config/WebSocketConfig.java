package com.liargame.liar.chap02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Stomp를 사용하기위해 선언하는 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat") // 핸드셰이크 커넥션을 생성할 경로
                .setAllowedOrigins("http://localhost:8181") // CORS
                .withSockJS(); // SockJS
    }

    /*어플리케이션 내부에서 사용할 path를 지정할 수 있음*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub"); // Client에서 SEND요청을 처리
        registry.enableSimpleBroker("/sub"); // 간단한 작업을 수행
    }
}

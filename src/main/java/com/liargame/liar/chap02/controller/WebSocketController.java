package com.liargame.liar.chap02.controller;

import com.liargame.liar.chap02.dto.request.AddChatRequestDTO;
import com.liargame.liar.chap02.dto.request.GameStateRequestDTO;
import com.liargame.liar.chap02.dto.request.GetOutPlayListDTO;
import com.liargame.liar.chap02.dto.request.RoomIdRequestDTO;
import com.liargame.liar.chap02.repository.Chat;
import com.liargame.liar.chap02.repository.Player;
import com.liargame.liar.chap02.repository.Room;
import com.liargame.liar.chap02.service.LiarGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final LiarGameService liarGameService;

    @MessageMapping("/sendMessage")
    public void responseChat(String s){
        System.out.println(s);
    }

    @MessageMapping("/giveMeList")
    @SendTo("/topic/list")
    public List<Player> responseList(@Payload GetOutPlayListDTO dto){
        System.out.println("서버로 받은 방 코드: "+dto.getRoomId());
        return liarGameService.findRoom(dto.getRoomId()).getPlayerList();
    }

    @MessageMapping("/outRoom")
    @SendTo("/topic/list")
    public List<Player> outList(@Payload GetOutPlayListDTO dto){
        liarGameService.getOutTheRoom(dto);
        return liarGameService.findRoom(dto.getRoomId()).getPlayerList();
    }

    @MessageMapping("/addChat")
    @SendTo("/topic/chat")
    public List<Chat> addChat(@Payload AddChatRequestDTO dto){
        Room room = liarGameService.findRoom(dto.getRoomId());
        room.addChat(dto.getUserName(), dto.getUserContext());
        return room.getChatList();
    }

    @MessageMapping("/giveMeChat")
    @SendTo("/topic/chat")
    public List<Chat> sendChatList(@Payload GetOutPlayListDTO dto){

        return liarGameService.findRoom(dto.getRoomId()).getChatList();
    }

    @MessageMapping("/gameStart")
    @SendTo("/topic/game")
    public String sendGameState(@Payload GameStateRequestDTO dto){
        System.out.println(dto.getText());
        return dto.getText();
    }

    @MessageMapping("/gameStarted")
    @SendTo("/topic/chat")
    public List<Chat> clearChat(@Payload RoomIdRequestDTO dto){
        // 채팅 리스트 비움
        Room room = liarGameService.findRoom(dto.getRoomId());
        room.setChatList(new ArrayList<>());
        return room.getChatList();
    }



}

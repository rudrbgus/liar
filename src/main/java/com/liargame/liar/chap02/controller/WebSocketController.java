package com.liargame.liar.chap02.controller;

import com.liargame.liar.chap02.dto.request.GetPlayListDTO;
import com.liargame.liar.chap02.repository.Player;
import com.liargame.liar.chap02.repository.Room;
import com.liargame.liar.chap02.service.LiarGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

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
    public List<Player> responseList(@Payload GetPlayListDTO dto){
        System.out.println("서버로 받은 방 코드: "+dto.getRoomId());
        return liarGameService.findRoom(dto.getRoomId()).getPlayerList();
    }

}

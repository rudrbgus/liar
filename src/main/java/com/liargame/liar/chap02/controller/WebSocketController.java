package com.liargame.liar.chap02.controller;

import com.liargame.liar.chap02.repository.Room;
import com.liargame.liar.chap02.service.LiarGameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketController {

    @MessageMapping("/sendMessage")
    public void responseChat(String s){
        System.out.println(s);
    }

    @MessageMapping("/giveMeList")
    @SendTo("/topic/list")
    public List<Room> responseList(){
        return LiarGameService.roomList;
    }
}

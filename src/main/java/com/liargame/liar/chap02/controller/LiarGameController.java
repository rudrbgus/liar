package com.liargame.liar.chap02.controller;

import com.liargame.liar.chap02.assets.userRandomEngName;
import com.liargame.liar.chap02.repository.User;
import com.liargame.liar.chap02.repository.WaitRoom;
import com.liargame.liar.chap02.service.LiarGameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LiarGameController {
    LiarGameService liarGameService = new LiarGameService();
    // 방 만들기
    @PostMapping("/create-room")
    public List<String> makeRoom(){
        return liarGameService.makeRoom();
    }
    @PostMapping("get-user-list")
    public void getUserListFromWaitRoom(@RequestBody String roomCode){
        liarGameService.getUserListFromWaitRoom(roomCode);
    }
}

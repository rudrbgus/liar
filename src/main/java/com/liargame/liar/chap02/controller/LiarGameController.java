package com.liargame.liar.chap02.controller;

import com.liargame.liar.chap02.assets.userRandomEngName;
import com.liargame.liar.chap02.repository.User;
import com.liargame.liar.chap02.repository.WaitRoom;
import com.liargame.liar.chap02.service.LiarGameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class LiarGameController {
    LiarGameService liarGameService;
    // 방 만들기
    @GetMapping("/create-room")
    public void makeRoom(){
        liarGameService.makeRoom();


    }
}

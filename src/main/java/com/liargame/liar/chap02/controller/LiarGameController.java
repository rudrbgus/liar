package com.liargame.liar.chap02.controller;

import com.liargame.liar.chap02.repository.Player;
import com.liargame.liar.chap02.repository.Room;
import com.liargame.liar.chap02.service.LiarGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LiarGameController {

    private final LiarGameService liarGameService;

    // 방 찾기
    @PostMapping("/room")
    public ResponseEntity<?> findRoom(@RequestBody Map<String, String> req){
        System.out.println(req.get("inputRoomCode"));
        return ResponseEntity.ok().body(liarGameService.findRoomAndAddPlayer(req.get("inputRoomCode")));
    }

    // 방 만들기
    @GetMapping("/room")
    public ResponseEntity<?> makeRoom(){
        return ResponseEntity.ok().body(liarGameService.makeRoom());
    }
    @PostMapping("/get-user-list")
    public ResponseEntity<?> getUserListFromWaitRoom(@RequestBody Map<String, String> req){
        return ResponseEntity.ok().body(liarGameService.getUserListFromRoom(req.get("roomCode")));
    }


    @PostMapping("/getUserNumber")
    public int sendUserNumber(@RequestBody Map<String, String> req){
        System.out.println(liarGameService.getUserNumber(req.get("roomCode")));
        return liarGameService.getUserNumber(req.get("roomCode"));
    }

    @PostMapping("/getSuperUserName")
    public String sendSuperUserName(@RequestBody Map<String, String> req){
        return liarGameService.getSuperUserId(req.get("roomCode"));
    }

}
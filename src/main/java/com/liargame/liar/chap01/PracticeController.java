package com.liargame.liar.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // React 애플리케이션의 주소로 변경
public class PracticeController {
    @GetMapping("/hello")
    public String getHello(){
        System.out.println("들어옴");
        return  "들어왔다";
    }

    @PostMapping("/create-room")
    public String makeRoom(@RequestBody Map<String, String> requestData){
        // 식별자와 방 코드를 입력 받음
        String temporaryIdentifier = requestData.get("temporaryIdentifier");
        String generateRandomRoomCode = requestData.get("randomRoomCode");
        // 임시 식별자를 저장하거나 필요한 로직을 수행합니다.
        System.out.println("받은 식별자: " + temporaryIdentifier);
        System.out.println("받은 방 코드: "+generateRandomRoomCode);


        userRandomName randomName = userRandomName.getRandomKoreanName();
        System.out.println("사용자에게 준 이름: "+randomName.name());

        // 방 객체 만들고 방에 이름 넣기
        Room room = new Room(generateRandomRoomCode);
        User user = new User(randomName.name(), temporaryIdentifier);
        room.addUserToRoom(user);
        RoomList roomList = new RoomList(room);
        System.out.println("방 만듬");
        return randomName.name();
    }

    @GetMapping("/find-room-code")
    public String compareRoomCode(@RequestBody Map<String, String> requestData){
        System.out.println("방 코드 입력함");
        String inputRoomCode = requestData.get("inputRoomCode"); // 방 코드
        String temporaryIdentifier = requestData.get("temporaryIdentifier"); // 식별자

        return "s";
    }

}

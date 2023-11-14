package com.liargame.liar.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // React 애플리케이션의 주소로 변경
public class PracticeController {
    @GetMapping("/hello")
    public String getHello(){
        System.out.println("들어옴");
        return  "들어왔다";
    }

    @GetMapping("/create-room")
    public String makeRoom(){
        System.out.println("방 만들기");
        return "만듬";
    }

    @PostMapping("/sendData")
    public String storeTemporaryIdentifier(@RequestBody String temporaryIdentifier) {
        // 임시 식별자를 저장하거나 필요한 로직을 수행합니다.
        System.out.println("Received temporary identifier: " + temporaryIdentifier);

        userRandomName randomName = userRandomName.getRandomKoreanName();
        System.out.println(randomName.name());
        return randomName.name();

        // 여기에서 응답을 보내지 않습니다.
    }
}

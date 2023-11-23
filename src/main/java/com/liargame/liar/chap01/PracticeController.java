package com.liargame.liar.chap01;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.liargame.liar.chap01.RoomList.*;
import static org.springframework.boot.web.server.Cookie.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // React 애플리케이션의 주소로 변경
public class PracticeController {


    @GetMapping("/hello")
    public String getHello(){
        return  "들어왔다";
    }

    @PostMapping("/create-room")
    public String makeRoom(){ // 방 만드는 메서드
        // 방 객체 만들고 방에 이름 넣기
        Room room = new Room(); // 새로운 방 만들기
        User user = new User(); // 새로운 유저 만들기
        room.addUserToRoom(user); // 방에 유저 넣기
        room.setSuperUserName(user.getName());
        addRoomList(room); // 새로운 방 리스트 만들기


        System.out.println("방장 이름: " + roomList.get(0).getUsers().get(0).getName());
        System.out.println("방 코드: " + roomList.get(0).getRoomId());
        System.out.println("방 만듬");
        System.out.println("사용자에게 쿠키로 준 이름: "+ user.getName());
        return user.getName();
    }

    @PostMapping("/compare-room-code")
    public String  compareRoomCode(@RequestBody Map<String, String> requestData){
        System.out.println("방 코드 입력함: " + requestData.get("inputRoomCode"));
        String inputRoomCode = requestData.get("inputRoomCode"); // 방 코드를 받음
        if(inputRoomCode.equals(roomList.get(0).getRoomId())){ // 방 코드가 일치하면
            System.out.println("방 코드 일치");
            User user = new User(); // 새로운 접속 유저
            System.out.println("새로운 유저 이름: " + user.getName());
            roomList.get(0).addUserToRoom(user); // 새로운 접속 유저 넣기
            return user.getName();
        }else{
            System.out.println("일치하는 방이 없습니다.");
            return "실패";
        }
    }

    @PostMapping("/get-user-list")
    public List<String> sendUserList() {
        List<User> users = roomList.get(0).getUsers();
        List<String> userNameList = new ArrayList<>();
        for (User user : users) {
            String name = user.getName();
            userNameList.add(name); // 리스트에 값을 추가하는 메서드는 add입니다.
        }
        return userNameList;
    }
    @PostMapping("/user-leave")
    public void userLeave(@RequestBody Map<String, String> requestBody){
        System.out.println("받은 쿠키 값: "+ requestBody.get("userId"));
        System.out.println("해독한 값: "+ decodeUrl(requestBody.get("userId")));
        String outUserName = decodeUrl(requestBody.get("userId"));
        for (int i = 0; i < roomList.get(0).getUsers().size(); i++) {
            if(outUserName.equals(roomList.get(0).getUsers().get(i).getName())){
                roomList.get(0).getUsers().remove(i);
                System.out.println("삭제함");
            }
        }
        System.out.println("유저가 나갔어 ㅠㅠ");
    }

    // 받아온 Uri 해독하는 메서드
    private String decodeUrl(String encodedValue) {
        try {
            return URLDecoder.decode(encodedValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 예외 처리 (UnsupportedEncodingException은 실제로 발생할 일이 거의 없습니다.)
            e.printStackTrace();
            return "";
        }
    }

    @PostMapping("/getRoomCode")
    public String sendRoomCode(){
        return roomList.get(0).getRoomId();
    }

    @PostMapping("/addChat")
    public void addChat(@RequestBody Map<String, String> requestBody){
        String userId = decodeUrl(requestBody.get("userId"));
        String userContext = requestBody.get("userContext");
        Chat chat = new Chat(userId, userContext);
        ChatArray.chatList.add(chat);
    }
    
    @PostMapping("/getChatList")
    public List<Chat> sendChatList(){
        return ChatArray.chatList;
    }

    @GetMapping("/getUserNumber")
    public int sendUserNumber(){
        if(roomList.get(0).getUsers().isEmpty())
        {
            return 0;
        }else{
            return roomList.get(0).getUsers().size();
        }
    }

    @GetMapping("/getSuperUserID")
    public String sendSuperUserID(){
        return roomList.get(0).getSuperUserName();
    }
}

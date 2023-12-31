package com.liargame.liar.chap01;

import com.liargame.liar.chap01.service.GameService;
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
    private static boolean started = false;
    private GameService gameService = new GameService();
    @PostMapping("/create-room")
    public String makeRoom(){ // 방 만드는 메서드
        // 방 객체 만들고 방에 이름 넣기
        Room room = new Room(); // 새로운 방 만들기
        User user = new User(); // 새로운 유저 만들기
        room.addUserToRoom(user); // 방에 유저 넣기
        room.setSuperUserName(user.getName());
        addRoomList(room); // 새로운 방 리스트 만들기

        System.out.println("--------------------방만들기--------------------------------");

        System.out.println("방장 이름: " + roomList.get(roomList.size()-1).getUsers().get(0).getName());
        System.out.println("방 코드: " + roomList.get(roomList.size()-1).getRoomId());
        System.out.println("사용자에게 쿠키로 준 이름: "+ user.getName());

        System.out.println("----------------------------------------------------------");
        return user.getName();
    }

    @PostMapping("/compare-room-code")
    public String  compareRoomCode(@RequestBody Map<String, String> requestData){
        // 방 코드 찾는 로직
        String roomCode = requestData.get("inputRoomCode");
        int roomNumber =-1;
        for (int i = 0; i < roomList.size(); i++) {
            boolean equals = roomList.get(i).getRoomId().equals(roomCode);
            if(equals){
                roomNumber = i;
            }
        }
        if(roomNumber == -1){
            return "실패";
        }

        System.out.println("사용자가 방 코드 입력함: " + requestData.get("inputRoomCode"));
        String inputRoomCode = requestData.get("inputRoomCode"); // 방 코드를 받음
        if(inputRoomCode.equals(roomList.get(roomNumber).getRoomId())){ // 방 코드가 일치하면
            System.out.println("방 코드 일치");
            User user = new User(); // 새로운 접속 유저
            System.out.println("새로운 유저 이름: " + user.getName());
            roomList.get(roomNumber).addUserToRoom(user); // 새로운 접속 유저 넣기
            return user.getName();
        }else{
            System.out.println("일치하는 방이 없습니다.");
            return "실패";
        }
    }

    @PostMapping("/get-user-list")
    public List<String> sendUserList(@RequestBody Map<String, String> requestData) {
        String roomCode = requestData.get("roomCode");
        int roomNumber =0;
        for (int i = 0; i < roomList.size(); i++) {
            roomList.get(i).getRoomId().equals(roomCode);
            roomNumber = i;
        }
        List<User> users = roomList.get(roomNumber).getUsers();
        List<String> userNameList = new ArrayList<>();
        for (User user : users) {
            String name = user.getName();
            userNameList.add(name); // 리스트에 값을 추가하는 메서드는 add입니다.
        }
        return userNameList;
    }
    @PostMapping("/user-leave")
    public void userLeave(@RequestBody Map<String, String> requestBody){
        System.out.println("--------------------사용자가 나갔습니다----------------------------");
        System.out.println("나간 사용자의 쿠키값: "+ requestBody.get("userId"));
        System.out.println("나간 사용자의 쿠키값을 해독한 값: "+ decodeUrl(requestBody.get("userId")));
        System.out.println("나간 사용자의 방 코드: "+ requestBody.get("roomCode"));
        String outUserName = decodeUrl(requestBody.get("userId"));
        String outUserRoomCode = requestBody.get("roomCode");
        for (int i = 0; i < roomList.get(0).getUsers().size(); i++) {
            if(outUserName.equals(roomList.get(0).getUsers().get(i).getName())){
                roomList.get(0).getUsers().remove(i);
                System.out.println("리스트에서 그 사용자를 삭제함");
                System.out.println(roomList.get(0).getUsers().toString());
                System.out.println("----------------------------------------------------------");
            }
        }
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
    public String sendRoomCode(@RequestBody Map<String, String> requestBody){
        String userName = requestBody.get("userName");
        int roomNumber =0;
        for (int i = 0; i < roomList.size(); i++) {
            roomList.get(i).getSuperUserName().equals(userName);
            roomNumber = i;
        }
        return roomList.get(roomNumber).getRoomId();
    }

    @PostMapping("/addChat")
    public void addChat(@RequestBody Map<String, String> requestBody){
        String userId = decodeUrl(requestBody.get("userId"));
        String userContext = requestBody.get("userContext");
        Chat chat = new Chat(userId, userContext);
        System.out.println("userId = " + userId);
        System.out.println("userContext = " + userContext);
        ChatArray.chatList.add(chat);
    }
    
    @PostMapping("/getChatList")
    public List<Chat> sendChatList(){
            return ChatArray.chatList;
    }

    @PostMapping("/getUserNumber")
    public int sendUserNumber(@RequestBody Map<String, String> requestBody){
        String roomCode = requestBody.get("roomCode");
        //System.out.println("입력받은 방 코드: "+ roomCode);
        int roomNumber =0;
        for (int i = 0; i < roomList.size(); i++) {
            if(roomList.get(i).getRoomId().equals(roomCode))
            {
                roomNumber = i;
            }
        }
        //System.out.println("방 인원: "+ roomList.get(roomNumber).getUsers().size());
        if(roomList.get(roomNumber).getUsers().size() == 0)
        {
            return 0;
        }else{
            return roomList.get(roomNumber).getUsers().size();
        }
    }

    @PostMapping("/getSuperUserID")
    public String sendSuperUserID(@RequestBody Map<String, String> req){
        String roomCode = req.get("roomCode");
        //System.out.println("입력받은 방 코드: "+ roomCode);
        int roomNumber =0;
        for (int i = 0; i < roomList.size(); i++) {
            if(roomList.get(i).getRoomId().equals(roomCode))
            {
                roomNumber = i;
            }
        }
        return roomList.get(roomNumber).getSuperUserName();
    }
    @GetMapping("/gameState")
    public boolean startGame(){
        return started;
    }

    @GetMapping("/gameStart")
    public void gameState(){
        started = true;
        System.out.println("게임 상태" + started);
    }

    //인게임 채팅
    @PostMapping("/addGameChat")
    public void addGameChat(@RequestBody Map<String, String> req){
        String userId = decodeUrl(req.get("userId"));
        String userContext = req.get("userContext");
        GameChat chat = new GameChat(userId, userContext);
        System.out.println("userId = " + userId);
        System.out.println("userContext = " + userContext);
        GameChatArray.gameChatList.add(chat);
    }
    @PostMapping("/getGameChatList")
    public List<GameChat> sendGameChatList(){
        return GameChatArray.gameChatList;
    }
    @PostMapping("/setLiar")
    public void setLiar(@RequestBody Map<String, String> req){
        System.out.println("-------------------------------------라이어 정하기------------------------------------");
        System.out.println(req.get("roomCode"));
        gameService.setLiar(req.get("roomCode"));
        System.out.println("-------------------------------------라이어 정하기------------------------------------");
    }

    @PostMapping("/getLiar")
    public Map<String, String> sendLiar(){
        return gameService.getLiar();
    }
    @PostMapping("/getPresentUser")
    public String sendFirstUser(@RequestBody Map<String, String> req){
        return gameService.getUserName(req.get("roomCode"));
    }

    @GetMapping("/increaseNum")
    public void increaseNum(){
        gameService.increaseNum();
    }
    @PostMapping("/getPrsentState")
    public String sendPresentState(@RequestBody Map<String, Integer> num){
        return gameService.getPresentState(num.get("num"));
    }
    @GetMapping("/getPrsentNumber")
    public int sendPresentNumber(){
        return gameService.getPresentNumber();
    }

    @PostMapping("/getGameState")
    public int sendGameState(){
        return gameService.getGameState();
    }

    @GetMapping("/addGameState")
    public void addGameState(){
        gameService.addGameState();
    }
    @PostMapping("/sendAnwser")
    public void getAnwser(@RequestBody Map<String, String> req){
        String s = req.get("userName");
        String s1 = req.get("cookieName");
        gameService.getAnwser(s, s1);
    }

}

package com.liargame.liar.chap02.service;

import com.liargame.liar.chap02.assets.userRandomEngName;
import com.liargame.liar.chap02.repository.User;
import com.liargame.liar.chap02.repository.WaitRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LiarGameService {
    // 대기방 리스트
    private static List<WaitRoom> waitRoomList;
    static {
        waitRoomList=new ArrayList<>();
    }

    // 임의의 4글짜 랜덤 코드 만드는 메서드 -> 방 코드 만들기
    private String generateRandomRoomId() {
        StringBuilder roomIdBuilder = new StringBuilder();
        Random random = new Random();

        // 4글자의 랜덤한 알파벳 대문자 생성
        for (int i = 0; i < 4; i++) {
            char randomChar = (char) ('A' + random.nextInt(26));
            roomIdBuilder.append(randomChar);
        }

        return roomIdBuilder.toString();
    }

    // 방 만드는 메서드
    public List<String> makeRoom() {
        User user = new User(userRandomEngName.getRandomEngName(), null, 0, true);// 방장 유저 생성
        WaitRoom waitRoom = new WaitRoom(generateRandomRoomId()); // 새로운 방 생성 -> 랜덤한 코드 삽입
        waitRoom.addUser(user);
        waitRoomList.add(waitRoom); // 새로운 대기방을 대기방 리스트에 삽입
        System.out.println("새로운 대기방이 생성 되었습니다");
        System.out.println("방 코드는: "+ waitRoom.getRoomCode());
        ArrayList<String> userInfo = new ArrayList<>(); // 유저한테 줘야하는 정보
        userInfo.add(user.getUserName());
        userInfo.add(waitRoom.getRoomCode());
        System.out.println("현재 방 리스트"+ waitRoomList.toString());
        return userInfo;
    }

    public List<String> getUserListFromWaitRoom(String roomCode) {

        for (WaitRoom w: waitRoomList) {
            if(w.getRoomCode().equals(roomCode)){
                return w.getUserNameList();
            }
        }
        return null;
    }
}
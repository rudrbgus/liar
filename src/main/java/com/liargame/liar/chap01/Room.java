package com.liargame.liar.chap01;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter @Setter
@ToString @EqualsAndHashCode
public class Room {
    // 방 제목
    private String roomId;

    // 사용자 리스트
    private List<User> users;

    private String superUserName;

    // 유저 이름을 받아서 리스트에 추가하는 메서드
    public void addUserToRoom(User  user) {
        if (users != null) {
            users.add(user);
        } else {
            System.out.println("Error: Users list is null.");
        }
    }

    // Room 클래스 생성시 users리스트 초기화
    public Room() {
        this.users = new ArrayList<>();
        this.roomId = generateRandomRoomId();
    }

    public Room(String roomId) {
        this.roomId = roomId;
        this.users = new ArrayList<>();
    }

    // 4글자의 임의의 방 이름 생성 메서드
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
}
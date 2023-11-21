package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Room {
    // 방 제목
    private String roomId;

    // 사용자 리스트
    private List<User> users;

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
    }

    public Room(String roomId) {
        this.roomId = roomId;
        this.users = new ArrayList<>();
    }
}
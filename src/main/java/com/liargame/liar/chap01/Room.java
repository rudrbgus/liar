package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class Room {
    // 방 제목
    private String roomId;

    // 사용자 리스트
    private List<String> users;

    // 유저 이름을 받아서 리스트에 추가하는 메서드
    public void addUserToRoom(String userName) {
        users.add(userName);
    }


}
package com.liargame.liar.chap02.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 대기실 클래스
@Setter @Getter
public class WaitRoom {
    private String roomCode; // 방 코드
    private WaitRoomChatArray waitRoomChatArray; // 방 채팅리스트
    private List<User> userList = new ArrayList<>();
    private List<String> userNameList = new ArrayList<>();



    public WaitRoom(String roomCode) {
        this.roomCode = roomCode;
    }
    public void addUser(User user){
        userList.add(user);
        userNameList.add(user.getUserName());
    }
}

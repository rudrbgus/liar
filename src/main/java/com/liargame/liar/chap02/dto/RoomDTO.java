package com.liargame.liar.chap02.dto;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/*
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
 */
public class RoomDTO {
    private String roomCode; // 방 코드
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static RoomDTO create(String name){
        RoomDTO room = new RoomDTO();

        room.roomCode = UUID.randomUUID().toString();
        return room;
    }
}

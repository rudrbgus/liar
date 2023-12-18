package com.liargame.liar.chap02.service;

import com.liargame.liar.chap02.assets.userRandomEngName;
import com.liargame.liar.chap02.repository.Player;
import com.liargame.liar.chap02.repository.Room;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Setter
@Service
public class LiarGameService {
    // 대기방 리스트
    public static List<Room> roomList;

    static {
        roomList = new ArrayList<>();
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

    // 방 코드 보내면 방 리스트에서 찾아서 방 보내줌 없으면 null 반환
    private Room getWaitRoom(String roomId) {
        for(Room r:roomList){
            if(r.getRoomId().equals(roomId)){
                return r;
            }
        }
        return null;
    }

    // 한국어 문자열 URL 인코딩
    private String enCodeingURL(String originalName){
        try{
            String encode = URLEncoder.encode(originalName, "UTF-8");
            return encode;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }


    // 방 만드는 메서드
    public String makeRoom() {
        Player user = new Player(userRandomEngName.getRandomEngName(), null, true);// 방장 유저 생성
        String roomId = generateRandomRoomId();
        Room room = new Room(roomId); // 새로운 방 생성 -> 랜덤한 코드 삽입
        room.addUser(user);
        roomList.add(room); // 새로운 대기방을 대기방 리스트에 삽입
        System.out.println("새로운 대기방이 생성 되었습니다");
        System.out.println("방 코드는: "+ room.getRoomId());
        return roomId;
    }

    public Room findRoom(String roomId){
        for (Room room : roomList) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public List<Player> getUserListFromRoom(String roomCode) {
        Room waitRoom = getWaitRoom(roomCode);
        if (waitRoom != null){
            return waitRoom.getPlayerList();
        }
        return null;
    }

    public int getUserNumber(String roomCode) {
        Room waitRoom = getWaitRoom(roomCode);
        if(waitRoom != null){
            if(waitRoom.getPlayerList() != null)
                return waitRoom.getPlayerList().size();
        }
        return 0;
    }


    public String getSuperUserId(String roomCode) {
        Room waitRoom = getWaitRoom(roomCode);
        for (Player p: waitRoom.getPlayerList()){
            if(p.isSuperPlayer()){
                return enCodeingURL(p.getPlayerId());
            }
        }
        return null;
    }



}
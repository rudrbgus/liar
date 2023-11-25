package com.liargame.liar.chap01.service;

import com.liargame.liar.chap01.GameChatArray;
import com.liargame.liar.chap01.RoomList;
import com.liargame.liar.chap01.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.liargame.liar.chap01.RoomList.roomList;

@Setter @Getter
public class GameService {
    private GameChatArray gameChatArray;
    private RoomList roomList;
    private int gameUserNumber;
    private Map<String, String> role = new HashMap<>();
    private int presentNum = 0;

    public void setLiar(String roomCode){
        System.out.println("게임 시작 하셨고요 방 코드는: "+ roomCode);
        int roomNumber =0;
        for (int i = 0; i < RoomList.roomList.size(); i++) {
            if(RoomList.roomList.get(i).getRoomId().equals(roomCode))
            {
                roomNumber = i;
            }
        }
        List<User> gameUsers = RoomList.roomList.get(roomNumber).getUsers();
        System.out.println("게임 참여한 사람의 수는: "+gameUsers.size());
        gameUserNumber = gameUsers.size();
        Random random = new Random();
        int liarNumber = random.nextInt(gameUserNumber);
        System.out.println("라이어 숫자는: "+ liarNumber);
        for (int i = 0; i < gameUserNumber; i++) {
            if(i==liarNumber){
                role.put(RoomList.roomList.get(roomNumber).getUsers().get(i).getName(), "라이어");
            }else {
                role.put(RoomList.roomList.get(roomNumber).getUsers().get(i).getName(), "시민");
            }
        }
        System.out.println("라이어 삽입이 완료 되었습니다: " + role.toString());
    }

    public Map<String, String> getLiar(){
        return role;
    }

    public String getUserName(String roomCode, String num){
        int roomNumber =0;
        for (int i = 0; i < RoomList.roomList.size(); i++) {
            if(RoomList.roomList.get(i).getRoomId().equals(roomCode))
            {
                roomNumber = i;
            }
        }

        return RoomList.roomList.get(roomNumber).getUsers().get(Integer.parseInt(num)).getName();
    }
    public int sendPresentNumber(){
        return presentNum;
    }



}

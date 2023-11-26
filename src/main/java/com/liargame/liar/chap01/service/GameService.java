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
    private Map<String, String> anwser = new HashMap<>();
    private int presentNum = 0;
    private String roomCode = "";
    private int gameState = 1;

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

    public String getUserName(String roomCode){
        this.roomCode = roomCode;
        int roomNumber =0;
        for (int i = 0; i < RoomList.roomList.size(); i++) {
            if(RoomList.roomList.get(i).getRoomId().equals(roomCode))
            {
                roomNumber = i;
            }
        }
        if(presentNum>=RoomList.roomList.get(roomNumber).getUsers().size()){
            presentNum =0;
        }
        return RoomList.roomList.get(roomNumber).getUsers().get(presentNum).getName();
    }

    public void increaseNum(){
        System.out.println("추가됨");
        presentNum+= 1;
    }
    public void addGameState(){
        gameState++;
    }
    public int getGameState(){
        return gameState;
    }

    public String getPresentState(int n) {
        System.out.println(n);
        switch (n){
            case 1:
                return "이제 게임이 시작됩니다";
            case 2:

            case 3:

            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:

            case 10:
                return getUserName(roomCode)+"님 제시어에 대해서 설명해주세요";
            case 11:
                return "이제 라이어를 지목해주세요!!";
            case 12:
                return "기다려주세요";
            case 13:
                return "기다려주세요";
            case 14:
                return "기다려주세요";
            case 15:
                anwser();
                return "라이어가 이겼습니다!!";

        }
        return "";
    }
    public int getPresentNumber(){
        return presentNum;
    }

    public void getAnwser(String anwserName, String userName ) {
        if(anwser.get(userName) == null){
            anwser.put(userName, anwserName);
            System.out.println(anwserName);
            System.out.println(anwser.get(userName));
            System.out.println(userName);
            System.out.println("정답 리스트에 담았습니다");
            System.out.println(anwser.toString());
        }else{
            System.out.println("이미 유저는 클릭했습니다");
        }

    }
    public void anwser(){
        String anwser1 = anwser.get(RoomList.roomList.get(0).getUsers().get(0).getName());
        String anwser2 = anwser.get(RoomList.roomList.get(0).getUsers().get(1).getName());

        System.out.println(anwser1);
        System.out.println(anwser2);
    }


}

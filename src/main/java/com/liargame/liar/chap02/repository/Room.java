package com.liargame.liar.chap02.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String roomId;
    private List<Player> playerList;
    private List<Chat> chatList;

    public Room(String s) {
        this.roomId = s;
        this.playerList = new ArrayList<>();
    }
    public void addUser(Player p){
        this.playerList.add(p);
    }
    public void delete(String userName){
        playerList.removeIf(p -> p.getPlayerId().equals(userName));
        System.out.println("리스트에서 삭제가 완료 되었습니다 : " + playerList.toString());
    }
}

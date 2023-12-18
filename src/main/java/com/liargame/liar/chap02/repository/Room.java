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
    private List<Player> playerList = new ArrayList<>();



    public Room(String s) {
        this.roomId = s;
        this.playerList = new ArrayList<>();
    }
    public void addUser(Player p){
        this.playerList.add(p);
    }
}

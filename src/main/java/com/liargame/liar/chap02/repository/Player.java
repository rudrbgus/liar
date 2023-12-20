package com.liargame.liar.chap02.repository;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Player {
    private String playerId;
    private String role;
    private boolean isSuperPlayer;

    public Player(String randomEngName, String role,  boolean isSuper) {
        this.playerId = randomEngName;
        this.role = role;
        this.isSuperPlayer = isSuper;
    }


}

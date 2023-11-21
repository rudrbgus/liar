package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String name; // 사용자 이름
    private String temporaryIdentifier; // 사용자 식별자

    public User(String name, String temporaryIdentifier) {
        this.name = name;
        this.temporaryIdentifier = temporaryIdentifier;
    }
}

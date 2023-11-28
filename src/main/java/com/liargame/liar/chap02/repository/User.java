package com.liargame.liar.chap02.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class User {
    private String userName; // 유저 이름
    private String role; // 유저의 역할
    private int vote; // 받은 투표 수
    private boolean superUser;
}

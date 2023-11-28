package com.liargame.liar.chap02.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Chat {
    public User user;
    public String chat;
}

package com.liargame.liar.chap02.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter @AllArgsConstructor
public class WaitRoomChatArray {
    public List<Chat> chatList;
}

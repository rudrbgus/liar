package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RoomList {
    private List<Room> roomList = new ArrayList<>();

    public RoomList(Room room) {
        roomList.add(room);
    }
    public void addRoomList(Room room){
        roomList.add(room);
    }
}

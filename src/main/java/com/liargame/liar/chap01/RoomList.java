package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class RoomList {
    public static List<Room> roomList;

    static{
        roomList = new ArrayList<>();
    }

    public static void addRoomList(Room room){
        roomList.add(room);
    }
}

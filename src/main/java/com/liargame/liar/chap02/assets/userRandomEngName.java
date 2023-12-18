package com.liargame.liar.chap02.assets;

import java.util.Random;

public enum userRandomEngName {
    OliviaGrace, EthanAlexander, ScarlettRose, MasonJames, AvaCeleste, LiamHarrison, IsabellaFaith, BenjaminCole,
    SophiaElise, NoahBennett, ChloeVictoria, JacksonReid, LilyAnne, CalebDominic, AmeliaPaige;

    private static final Random RANDOM = new Random();


    public static String getRandomEngName() {
        userRandomEngName[] values = userRandomEngName.values();
        return values[RANDOM.nextInt(values.length)].toString();
    }
}

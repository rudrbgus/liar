package com.liargame.liar.chap01;

import java.util.Random;

public enum userRandomName {
    홍길동, 김철수, 이영희, 박민수, 최미영, 정태우, 송지영, 강성호, 유은지, 임종호, 한태용, 정범준, 경규현, 조로, 루피, 쵸파, 나루토, 사스케, 서준석, 베이가, 초가스, 모데카이저, 룰루, 카이사;

    private static final Random RANDOM = new Random();



    public static userRandomName getRandomKoreanName() {
        userRandomName[] values = userRandomName.values();
        return values[RANDOM.nextInt(values.length)];
    }
}
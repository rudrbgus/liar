package com.liargame.liar.chap01;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String name; // 사용자 이름
    private String temporaryIdentifier; // 사용자 식별자

    public User() {
        this.name = createRandomUser();
        this.temporaryIdentifier = generateRandomIdentifier();
    }

    // 랜덤한 이름과 식별자를 설정하는 메서드
    public static String createRandomUser() {
        String randomName = userRandomName.getRandomKoreanName().toString();

        return randomName;
    }

    // 적절한 방식으로 랜덤 식별자 생성 메서드 (예: UUID 또는 다른 방법 활용)
    private static String generateRandomIdentifier() {
        // 여기에서 적절한 방식으로 랜덤 식별자를 생성하여 반환
        // 예: UUID 사용
        return java.util.UUID.randomUUID().toString();
    }
}

package com.liargame.liar.chap02.dto.response;

import lombok.*;

@Getter @ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponseDTO {
    String userName;
    String roomId;

}

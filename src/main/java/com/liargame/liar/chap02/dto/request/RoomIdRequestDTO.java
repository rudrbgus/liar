package com.liargame.liar.chap02.dto.request;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomIdRequestDTO {
    private String roomId;
}

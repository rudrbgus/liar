package com.liargame.liar.chap02.dto.request;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddChatRequestDTO {
    private String roomId;
    private String userName;
    private String userContext;
}

package com.liargame.liar.chap02.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class GetOutPlayListDTO {
    private String userName;
    private String roomId;


}

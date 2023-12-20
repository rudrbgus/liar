package com.liargame.liar.chap02.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetPlayListDTO {
    private String roomId;

    public GetPlayListDTO(String roomId) {
        this.roomId = roomId;
    }
}

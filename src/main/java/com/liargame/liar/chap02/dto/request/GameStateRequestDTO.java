package com.liargame.liar.chap02.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
public class GameStateRequestDTO {
    private String text;
}

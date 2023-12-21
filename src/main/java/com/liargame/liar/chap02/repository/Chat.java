package com.liargame.liar.chap02.repository;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    private String userName;
    private String content;
}

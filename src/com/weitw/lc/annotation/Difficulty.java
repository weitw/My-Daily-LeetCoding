package com.weitw.lc.annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {
    Easy("简单"),Medium("中等"),Hard("困难");

    private String val;

}

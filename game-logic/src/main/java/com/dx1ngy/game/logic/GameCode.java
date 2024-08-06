package com.dx1ngy.game.logic;

import com.iohao.game.action.skeleton.core.exception.MsgExceptionInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum GameCode implements MsgExceptionInfo {
    ;

    final int code;
    final String msg;

    GameCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

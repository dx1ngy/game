package com.dx1ngy.game.logic.action;

import com.dx1ngy.game.proto.HelloReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActionController(1)
public class GameAction {
    @ActionMethod(0)
    public HelloReq _10(HelloReq helloReq) {
        // 需要给请求端响应的业务数据
        HelloReq newHelloReq = new HelloReq();
        newHelloReq.name = helloReq.name + ", I'm here ";
        log.info("helloReq: {}", helloReq);
        return newHelloReq;
    }
}

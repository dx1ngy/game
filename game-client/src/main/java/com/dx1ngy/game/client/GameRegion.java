package com.dx1ngy.game.client;

import com.dx1ngy.game.proto.HelloReq;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        // 模拟请求的主路由
        inputCommandCreate.cmd = 1;

        ofCommand(0).setTitle("here").setRequestData(() -> {
            HelloReq helloReq = new HelloReq();
            helloReq.name = "1";
            return helloReq;
        }).callback(result -> {
            HelloReq value = result.getValue(HelloReq.class);
            log.info("value : {}", value);
        });
    }
}

package com.dx1ngy.game.client;

import com.dx1ngy.game.proto.LoginReq;
import com.dx1ngy.game.proto.LoginResp;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        // 模拟请求的主路由
        inputCommandCreate.cmd = 1;

        ofCommand(0).setTitle("login").setRequestData(() -> {
            LoginReq req = new LoginReq();
            req.userId = 10000;
            return req;
        }).callback(result -> {
            LoginResp resp = result.getValue(LoginResp.class);
            log.info("resp : {}", resp);
        });
    }
}

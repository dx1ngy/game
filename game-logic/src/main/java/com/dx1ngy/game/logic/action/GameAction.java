package com.dx1ngy.game.logic.action;

import com.dx1ngy.game.proto.HelloReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActionController(1)
public class GameAction {
    @ActionMethod(0)
    public HelloReq login(HelloReq helloReq, FlowContext flowContext) {
        long userId = 10000;
//        boolean existUser = ExternalCommunicationKit.existUser(userId);
//        if (existUser) {
//            // （相当于顶号），强制断开之前的客户端连接，并让本次登录成功。
//            ExternalCommunicationKit.forcedOffline(userId);
//        }
        boolean flag = UserIdSettingKit.settingUserId(flowContext, userId);
        if (!flag) {
            log.info("登陆失败");
        }
        // 需要给请求端响应的业务数据
        HelloReq newHelloReq = new HelloReq();
        newHelloReq.name = helloReq.name + ", I'm here ";
        log.info("helloReq: {}", helloReq);
        return newHelloReq;
    }
}

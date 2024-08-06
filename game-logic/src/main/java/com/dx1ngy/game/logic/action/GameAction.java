package com.dx1ngy.game.logic.action;

import com.dx1ngy.game.proto.LoginReq;
import com.dx1ngy.game.proto.LoginResp;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActionController(1)
public class GameAction {
    @ActionMethod(0)
    public LoginResp login(LoginReq req, FlowContext flowContext) {
        boolean existUser = ExternalCommunicationKit.existUser(req.userId);
        if (existUser) {
            // （相当于顶号），强制断开之前的客户端连接，并让本次登录成功。
            ExternalCommunicationKit.forcedOffline(req.userId);
        }
        boolean flag = UserIdSettingKit.settingUserId(flowContext, req.userId);
        if (!flag) {
            log.error("登录失败");
        }
        LoginResp resp = new LoginResp();
        resp.userId = req.userId;
        return resp;
    }
}

package com.dx1ngy.game.logic.action;

import com.dx1ngy.game.proto.LoginReq;
import com.dx1ngy.game.proto.LoginResp;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.commumication.ProcessorContext;
import com.iohao.game.action.skeleton.core.exception.MsgException;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.action.skeleton.protocol.processor.EndPointLogicServerMessage;
import com.iohao.game.action.skeleton.protocol.processor.EndPointOperationEnum;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ActionController(1)
public class GameAction {

    /**
     * 登录
     *
     * @param req         登录请求
     * @param flowContext 上下文
     * @return 登录响应
     */
    @ActionMethod(0)
    public LoginResp login(LoginReq req, FlowContext flowContext) {
        if (flowContext.getUserId() > 0) {
            throw new MsgException(100, "不能重复登录");
        }
        boolean existUser = ExternalCommunicationKit.existUser(req.userId);
        if (existUser) {
            // （相当于顶号），强制断开之前的客户端连接，并让本次登录成功。
            ExternalCommunicationKit.forcedOffline(req.userId);
        }
        boolean flag = UserIdSettingKit.settingUserId(flowContext, req.userId);
        if (!flag) {
            throw new MsgException(101, "登录失败");
        }
        List<Long> userIds = new ArrayList<>();
        userIds.add(req.userId);
        // 绑定消息
        EndPointLogicServerMessage endPointLogicServerMessage = new EndPointLogicServerMessage()
                // 需要绑定的玩家，示例中只取了当前请求匹配的玩家
                .setUserList(userIds)
                /*
                 * 添加需要绑定的逻辑服id，下面绑定了两个；
                 * 1.给绑定一个房间游戏逻辑服的 id
                 * 2.绑定 animal 游戏逻辑服就简单点，固定写 id 为 2-1 的；
                 * （也就是我们启动两台 animal 游戏逻辑服中的一台）
                 */
                .addLogicServerId("logic-1")
                // 覆盖绑定游戏逻辑服
                .setOperation(EndPointOperationEnum.COVER_BINDING);

// 发送消息到网关
        ProcessorContext processorContext = BrokerClientHelper.getProcessorContext();
        processorContext.invokeOneway(endPointLogicServerMessage);


        LoginResp resp = new LoginResp();
        resp.userId = req.userId;
        return resp;
    }

    /**
     * 测试
     *
     * @param flowContext 上下文
     */
    @ActionMethod(1)
    public void test(LoginReq req, FlowContext flowContext) {
        System.out.println(req);
//        for (int i = 0; i < 100; i++) {
//            resp.userId = i;
//            flowContext.broadcastMe(CmdInfo.of(1, 1), resp);
//        }
    }
}

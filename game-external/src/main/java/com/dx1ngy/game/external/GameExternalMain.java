package com.dx1ngy.game.external;

import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.common.kit.trace.TraceKit;
import com.iohao.game.external.core.ExternalServer;
import com.iohao.game.external.core.config.ExternalGlobalConfig;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import com.iohao.game.external.core.hook.internal.DefaultUserHook;
import com.iohao.game.external.core.netty.DefaultExternalCoreSetting;
import com.iohao.game.external.core.netty.DefaultExternalServer;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class GameExternalMain {
    public static void main(String[] args) {
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
        IoGameGlobalConfig.openTraceId = true;
        var accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        // 表示登录才能访问业务方法
        accessAuthenticationHook.setVerifyIdentity(true);
        // 添加不需要登录（身份验证）也能访问的业务方法 (action)
        accessAuthenticationHook.addIgnoreAuthCmd(1, 0);

        TraceKit.setDefaultTraceIdSupplier(() -> UUID.randomUUID().toString());

        int externalCorePort = 10100;
        // 创建游戏对外服构建器
        var builder = DefaultExternalServer
                // 游戏对外服端口；与真实玩家建立连接的端口
                .newBuilder(externalCorePort)
                // 连接方式 WebSocket；默认不填写也是这个值
                .externalJoinEnum(ExternalJoinEnum.WEBSOCKET)
                // 与 Broker （游戏网关）的连接地址 ；默认不填写也是这个值
                .brokerAddress(new BrokerAddress("127.0.0.1", IoGameGlobalConfig.brokerPort));

        // 得到 setting 对象（开发者可根据自身业务做扩展）
        DefaultExternalCoreSetting setting = builder.setting();
        //用户上下线钩子
        setting.setUserHook(new DefaultUserHook());
        //心跳
//        IdleProcessSetting idleProcessSetting = new IdleProcessSetting();
//        idleProcessSetting.setIdleTime(300)
//                .setIdleHook(new DefaultSocketIdleHook());
//
//        setting.setIdleProcessSetting(idleProcessSetting);
        // 构建、启动
        ExternalServer externalServer = builder.build();
        externalServer.startup();
    }
}
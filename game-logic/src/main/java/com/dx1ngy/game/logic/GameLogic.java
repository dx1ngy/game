package com.dx1ngy.game.logic;

import com.dx1ngy.game.logic.action.GameAction;
import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.action.skeleton.core.flow.internal.TraceIdInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameLogic extends AbstractBrokerClientStartup {
    @Override
    public BarSkeleton createBarSkeleton() {
        // 业务框架构建器 配置
        BarSkeletonBuilderParamConfig config = new BarSkeletonBuilderParamConfig()
                // 扫描 GameAction.class 所在包
                .scanActionPackage(GameAction.class);

        // 业务框架构建器
        BarSkeletonBuilder builder = config.createBuilder();
        // 全链路调用日志跟踪插件，将插件添加到业务框架中
        builder.addInOut(new TraceIdInOut());
        // 添加控制台输出插件
        DebugInOut debugInOut = new DebugInOut();
        debugInOut.setPrintConsumer((message, flowContext) -> {
            log.info("\n{}", message);
        });
        builder.addInOut(debugInOut);
        return builder.build();
    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        return BrokerClient.newBuilder()
                .appName("游戏逻辑服");
    }

    @Override
    public BrokerAddress createBrokerAddress() {
        int brokerPort = IoGameGlobalConfig.brokerPort;
        return new BrokerAddress("127.0.0.1", brokerPort);
    }
}

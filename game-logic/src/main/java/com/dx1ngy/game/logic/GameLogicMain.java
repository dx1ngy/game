package com.dx1ngy.game.logic;

import com.dx1ngy.game.proto.LoginResp;
import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.action.skeleton.core.doc.BroadcastDocument;
import com.iohao.game.action.skeleton.core.doc.IoGameDocumentHelper;
import com.iohao.game.action.skeleton.core.doc.TextDocumentGenerate;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;

public class GameLogicMain {
    public static void main(String[] args) {
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
        BrokerClientApplication.start(new GameLogic());
        generateDocument();
    }

    private static void generateDocument() {
        BroadcastDocument.newBuilder(CmdInfo.of(2, 0))
                // 广播（推送）的数据类型
                .setDataClass(LoginResp.class)
                // 广播（推送）描述
                .setMethodDescription("登录推送消息")
                // 构建广播文档对象，并添加到文档中
                .buildToDocument();

        // 添加自定义的文档生成器
        IoGameDocumentHelper.addDocumentGenerate(new TextDocumentGenerate());
        // 添加枚举错误码 class，用于生成错误码相关信息
        IoGameDocumentHelper.addErrorCodeClass(GameCode.class);
        // 生成文档
        IoGameDocumentHelper.generateDocument();
    }
}
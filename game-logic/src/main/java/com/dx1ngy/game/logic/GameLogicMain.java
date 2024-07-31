package com.dx1ngy.game.logic;

import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;

public class GameLogicMain {
    public static void main(String[] args) {
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
        BrokerClientApplication.start(new GameLogic());
    }
}
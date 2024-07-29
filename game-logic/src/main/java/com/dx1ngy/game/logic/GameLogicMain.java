package com.dx1ngy.game.logic;

import com.iohao.game.bolt.broker.client.BrokerClientApplication;

public class GameLogicMain {
    public static void main(String[] args) {
        BrokerClientApplication.start(new GameLogic());
    }
}
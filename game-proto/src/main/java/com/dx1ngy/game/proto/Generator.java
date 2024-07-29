package com.dx1ngy.game.proto;

import com.iohao.game.common.kit.ArrayKit;
import com.iohao.game.widget.light.protobuf.kit.GenerateFileKit;

import java.io.File;

public class Generator {
    public static void main(String[] args) {
        String packagePath = "com.dx1ngy.game.proto";
        String currentDir = System.getProperty("user.dir");
        // 生成 .proto 文件存放的目录
        String generateFolder = ArrayKit.join(new String[]{
                currentDir,
                "game-proto",
                "target",
                "proto"
        }, File.separator);
        GenerateFileKit.generate(packagePath, generateFolder);
    }
}

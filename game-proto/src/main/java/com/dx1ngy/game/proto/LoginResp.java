package com.dx1ngy.game.proto;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 登录响应
 */
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "one.proto", filePackage = "pb.one")
@ToString
public class LoginResp {
    /**
     * 用户id
     */
    long userId;
}

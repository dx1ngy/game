package com.dx1ngy.game.proto;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 登录请求
 */
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "one.proto", filePackage = "pb.one")
@ToString
public class LoginReq {
    /**
     * 用户id
     */
    @Min(1)
    long userId;
}

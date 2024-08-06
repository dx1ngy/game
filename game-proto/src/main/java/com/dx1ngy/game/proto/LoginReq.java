package com.dx1ngy.game.proto;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "one.proto", filePackage = "pb.one")
@ToString
public class LoginReq {
    @Min(1)
    long userId;
}

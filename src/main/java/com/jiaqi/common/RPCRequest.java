package com.jiaqi.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCRequest implements Serializable {
    // 接口名
    public String interfaceName;
    // 要请求的方法名
    public String methodName;
    // 方法所需要的参数
    private Object[] params;
    // 参数的类型
    private Class<?>[] paramsTypes;
}

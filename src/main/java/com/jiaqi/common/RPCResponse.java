package com.jiaqi.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCResponse implements Serializable {
    public int code;
    public String message;

    private Object data;

    public static RPCResponse Success(Object data) {
        return RPCResponse.builder()
                .code(200)
                .message("successfully response.")
                .data(data)
                .build();
    }

    public static RPCResponse failed() {
        return RPCResponse.builder()
                .code(500)
                .message("something wrong with server.")
                .build();
    }
}

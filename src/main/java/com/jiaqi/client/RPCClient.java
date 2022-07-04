package com.jiaqi.client;

import com.jiaqi.common.RPCRequest;
import com.jiaqi.common.RPCResponse;

public interface RPCClient {
    public RPCResponse sendRequest(RPCRequest request);
}

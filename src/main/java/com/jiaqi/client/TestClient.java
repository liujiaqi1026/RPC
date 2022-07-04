package com.jiaqi.client;

import com.jiaqi.common.User;
import com.jiaqi.service.UserService;

public class TestClient {
    public static void main(String[] args) {
//        RPCClient simpleRPCClient = new SimpleRPCClient("127.0.0.1", 8899);
        RPCClient nettyRPCClient = new NettyRPCClient("127.0.0.1", 8899);

        ClientProxy clientProxy = new ClientProxy(nettyRPCClient);

        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getUserByUserId(2222);

        System.out.println("User:" + user);
    }
}

package com.jiaqi.server;

import com.jiaqi.service.BlogService;
import com.jiaqi.service.BlogServiceImpl;
import com.jiaqi.service.UserService;
import com.jiaqi.service.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

//        RPCServer rpcServer = new SimpleRPCServer(serviceProvider);
//        RPCServer rpcServer = new ThreadPoolRPCServer(serviceProvider);
        RPCServer rpcServer = new NettyRPCServer(serviceProvider);
        rpcServer.start(8899);
    }
}

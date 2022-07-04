package com.jiaqi.client;

import com.jiaqi.common.Blog;
import com.jiaqi.common.User;
import com.jiaqi.server.BlogServiceImpl;
import com.jiaqi.service.BlogService;
import com.jiaqi.service.UserService;

public class RPCClient {
    public static void main(String[] args) {
        // 通过反射拿到想要的proxy接口。
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 触发重写的invoke函数。
        System.out.println("客户端发起请求，通过UserId请求User。");
        User user = proxy.getUserByUserId(10);
        System.out.println("返回的User。" + user);

        System.out.println("客户端发起请求，插入User。");
        Integer userId = proxy.insertUserId(User.builder().sex(true).userName("liujiaqi").id(111111).build());
        System.out.println("返回的userId." + userId);

        BlogService proxy2 = clientProxy.getProxy(BlogService.class);
        Blog blog = proxy2.getBlogById(22222);
        System.out.println(blog);
    }
}

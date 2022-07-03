package com.jiaqi.server;

import com.jiaqi.common.RPCRequest;
import com.jiaqi.common.RPCResponse;
import com.jiaqi.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动了");

            //BIO方式来监听端口
            while(true) {

                    Socket socket = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            ObjectOutputStream oops = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream oips = new ObjectInputStream(socket.getInputStream());

                            RPCRequest request = (RPCRequest) oips.readObject();
                            Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                            Object object = method.invoke(userService, request.getParams());


                            oops.writeObject(RPCResponse.Success(object));
                            oops.flush();
                        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |IllegalAccessException e) {
                            e.printStackTrace();
                            System.out.println("从IO中读取数据错误");
                        }
                    }).start();
                }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }
}

package com.jiaqi.server;

import com.google.errorprone.annotations.Var;
import com.jiaqi.common.RPCRequest;
import com.jiaqi.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            RPCRequest request = (RPCRequest) is.readObject();

            RPCResponse response = getResponse(request);

            os.writeObject(response);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        Object service = serviceProvider.getService(request.getInterfaceName());
        try {
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object object = method.invoke(service, request.getParams());

            return RPCResponse.Success(object);
        } catch (Exception e) {
            e.printStackTrace();
            return RPCResponse.failed();
        }
    }
}

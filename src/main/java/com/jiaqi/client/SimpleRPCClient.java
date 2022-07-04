package com.jiaqi.client;

import com.jiaqi.common.RPCRequest;
import com.jiaqi.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCClient implements RPCClient {
    private String host;
    private int port;
    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            os.writeObject(request);
            os.flush();

            RPCResponse response = (RPCResponse) is.readObject();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

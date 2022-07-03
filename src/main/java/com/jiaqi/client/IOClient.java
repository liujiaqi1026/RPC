package com.jiaqi.client;

import com.jiaqi.common.RPCRequest;
import com.jiaqi.common.RPCResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {

    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream ops = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ips = new ObjectInputStream(socket.getInputStream());

            ops.writeObject(request);
            ops.flush();

            return (RPCResponse) ips.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.jiaqi.server;

import com.jiaqi.common.RPCRequest;
import com.jiaqi.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.Method;

@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {

    private ServiceProvider serviceProvider;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest request) throws Exception {
        RPCResponse response = getResponse(request);
        channelHandlerContext.writeAndFlush(response);
        channelHandlerContext.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private RPCResponse getResponse(RPCRequest request) {
        try {
            Object service = serviceProvider.getService(request.getInterfaceName());
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());

            return RPCResponse.Success(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            return RPCResponse.failed();
        }
    }
}

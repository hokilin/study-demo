package cn.hokilin.studydemo.rpc.transmission.netty;

import cn.hokilin.studydemo.rpc.transmission.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author linhuankai
 * @date 2021/2/3 21:06
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, RpcResponse response) {
        //处理响应
        System.out.println(response.getRequestId());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

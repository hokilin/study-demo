package cn.hokilin.studydemo.rpc.transmission.netty;

import cn.hokilin.studydemo.rpc.transmission.RpcRequest;
import cn.hokilin.studydemo.rpc.transmission.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author linhuankai
 * @date 2021/2/3 21:05
 * https://www.cnkirito.moe/rpc-transport/
 */
public class RpcNettyConsumer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建并初始化 Netty 客户端 Bootstrap 对象
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new RpcEncoder(RpcRequest.class)); // 编码 RPC 请求
                    pipeline.addLast(new RpcDecoder(RpcResponse.class)); // 解码 RPC 响应
                    pipeline.addLast(new RpcClientHandler()); // 处理 RPC 响应
                }
            });
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            // 连接 RPC 服务器
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8087).sync();
            // 写入 RPC 请求数据并关闭连接
            Channel channel = future.channel();

            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setRequestId(123456L);

            channel.writeAndFlush(rpcRequest).sync();
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}

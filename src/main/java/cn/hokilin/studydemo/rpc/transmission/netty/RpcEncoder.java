package cn.hokilin.studydemo.rpc.transmission.netty;

import cn.hokilin.studydemo.serializer.Hessian2Serialization;
import cn.hokilin.studydemo.serializer.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author linhuankai
 * @date 2021/2/3 21:04
 * https://www.cnkirito.moe/rpc-transport/
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    Serialization serialization = new Hessian2Serialization();

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = serialization.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}

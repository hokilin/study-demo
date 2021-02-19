package cn.hokilin.studydemo.rpc.transmission;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2021/2/1 22:54
 */
@Data
public class RpcResponse implements Serializable {
    private Object value;
    private Exception exception;
    private long requestId;
    private long processTime;
    private int timeout;
    /**
     * rpc协议版本兼容时可以回传一些额外的信息
     */
    private Map<String, String> attachments;
    private byte rpcProtocolVersion;
}

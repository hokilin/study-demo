package cn.hokilin.studydemo.rpc.transmission;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2021/2/1 22:53
 */
@Data
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private String parametersDesc;
    private Object[] arguments;
    private Map<String, String> attachments;
    private int retries = 0;
    private long requestId;
    private byte rpcProtocolVersion;
}

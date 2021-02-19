package cn.hokilin.studydemo.rpc;

/**
 * @author linhuankai
 * @date 2021/1/31 11:38
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}

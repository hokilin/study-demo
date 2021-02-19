package cn.hokilin.studydemo.rpc;

/**
 * @author linhuankai
 * @date 2021/1/31 11:38
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}

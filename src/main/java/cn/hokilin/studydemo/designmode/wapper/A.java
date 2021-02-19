package cn.hokilin.studydemo.designmode.wapper;

/**
 * @author linhuankai
 * @date 2021/2/3 18:33
 */
public class A extends AllWrapper {

    protected A(All all) {
        super(all);
    }

    @Override
    public void a() {
        System.out.println("A - a");
    }

    @Override
    public void X() {
        System.out.println("A - X");
        super.X();
    }
}

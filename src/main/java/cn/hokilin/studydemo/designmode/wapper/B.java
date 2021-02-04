package cn.hokilin.studydemo.designmode.wapper;

/**
 * @author linhuankai
 * @date 2021/2/3 18:34
 */
public class B extends AllWrapper {

    protected B(All all) {
        super(all);
    }

    @Override
    public void b() {
        System.out.println("B - b");
    }

    @Override
    public void X() {
        System.out.println("B - X");
        super.X();
    }
}
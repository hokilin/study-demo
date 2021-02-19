package cn.hokilin.studydemo.designmode.wapper;

/**
 * @author linhuankai
 * @date 2021/2/3 18:34
 */
public class C extends AllWrapper {

    protected C(All all) {
        super(all);
    }

    @Override
    public void c() {
        System.out.println("C - c");
    }

    @Override
    public void X() {
        System.out.println("C - X");
        super.X();
    }
}
package cn.hokilin.studydemo.designmode.wapper;

/**
 * @author linhuankai
 * @date 2021/2/3 18:32
 */
public abstract class AllWrapper extends All {
    private All under;

    protected AllWrapper(All all) {
        under = all;
    }

    @Override
    public void a() {
        under.a();
    }

    @Override
    public void b() {
        under.b();
    }

    @Override
    public void c() {
        under.c();
    }

    @Override
    public void X() {
        under.X();
    }
}

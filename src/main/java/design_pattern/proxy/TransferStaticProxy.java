package design_pattern.proxy;

/**
 * @author jerrysheh
 * @date 2019/9/7
 * 转账代理人，只可以代理转账
 */
public class TransferStaticProxy implements Transfer {

    private Transfer transfer;

    public TransferStaticProxy(Transfer transfer) {
        this.transfer = transfer;
    }

    @Override
    public void transfer(int amount) {
        before();
        this.transfer.transfer(amount);
        after();
    }

    private void before(){
        System.out.println("转账前检查账户信息");
    }

    private void after(){
        System.out.println("转账后显示账户余额");
    }

}

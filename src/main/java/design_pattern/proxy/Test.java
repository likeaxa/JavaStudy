package design_pattern.proxy;

/**
 * @author jerrysheh
 * @date 2019/9/7
 * 只要把转账方式通过构造器传给代理人，代理人就可以帮我们搞定
 */
public class Test {

    public static void main(String[] args) {

        // 支付宝转账 100 元，由代理人帮忙搞定转账前 检查账户 和转账后 显示余额
        Transfer aliTransfer = new AliPayTransfer();
        Transfer transferProxy = new TransferStaticProxy(aliTransfer);
        transferProxy.transfer(100);

    }

}

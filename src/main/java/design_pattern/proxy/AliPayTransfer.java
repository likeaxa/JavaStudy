package design_pattern.proxy;

/**
 * @author jerrysheh
 * @date 2019/9/7
 */
public class AliPayTransfer implements Transfer {
    @Override
    public void transfer(int amount) {
        System.out.println("使用支付宝转出了 " + amount + " 元");
    }

}
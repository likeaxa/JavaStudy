package concurrent.test4_stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 15:21
 */

/**
 * 总结：
 * 1 ： StampedLock 在命名上并没有增加 Reentrant，想必你已经猜测到 StampedLock 应该是不可重
 * 入的。事实上，的确是这样的，StampedLock 不支持重入。
 * 2 ： 如果线程阻塞在 StampedLock 的 readLock() 或者 writeLock()
 * 上时，此时调用该阻塞线程的 interrupt() 方法，会导致 CPU 飙升。
 * 使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的
 * 悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()。
 */
public class Test {

    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        // 获取写锁
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            // 释放写锁
            stampedLock.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        // 获得一个乐观读锁
        long stamp = stampedLock.tryOptimisticRead();
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)

        // 检查乐观读锁后是否有其他写锁发生
        if (!stampedLock.validate(stamp)) {
            // 获取一个悲观读锁
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                // 释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}

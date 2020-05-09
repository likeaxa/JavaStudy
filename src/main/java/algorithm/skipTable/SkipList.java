package algorithm.skipTable;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 18:02
 */
@Data
public class SkipList<T> {


    private static final int MAX_LEVEL = 1 << 6;

    /**
     * 跳跃表数据结构
     */
    private SkipNode<T> top;
    private int level = 0;
    /**
     * 用于产生随机数的Random对象
     */
    private Random random = new Random();

    public SkipList() {
        //创建默认初始高度的跳跃表
        this(4);
    }

    /**
     * 跳跃表的初始化
     *
     * @param level 跳跃表的高度
     */
    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipNode<T> temp = null;
        SkipNode<T> prev = null;
        while (i-- != 0) {
            temp = new SkipNode<T>(null, Double.MIN_VALUE);
            temp.down = prev;
            prev = temp;
        }

        //头节点
        top = temp;
    }

    public void put(double score, T val) {
        //1，找到需要插入的位置
        SkipNode<T> cur = null;
        //记录每一层当前节点的前驱节点
        List<SkipNode<T>> path = new ArrayList<>();
        //若cur不为空，表示当前score值的节点存在
        cur = this.get(top, score, path);
        if (cur != null) {
            while (cur != null) {
                cur.val = val;
                cur = cur.down;
            }
        } else {
            //当前表中不存在score值的节点，需要从下到上插入
            int lev = getRandomLevel();
            //需要更新top这一列的节点数量，同时需要在path中增加这些新的首节点
            if (lev > level) {
                SkipNode<T> temp = null;
                //前驱节点现在是top了
                SkipNode<T> prev = top;
                while (level++ != lev) {
                    temp = new SkipNode<T>(null, Double.MIN_VALUE);
                    //加到path的首部
                    path.add(0, temp);
                    temp.down = prev;
                    prev = temp;
                }
                //头节点
                top = temp;
                //level长度增加到新的长度
                level = lev;
            }
            //从后向前遍历path中的每一个节点，在其后面增加一个新的节点 递归过程先记录了高level的节点
            SkipNode<T> downTemp = null, temp = null, prev = null;
            for (int i = level - 1; i >= level - lev; i--) {
                temp = new SkipNode<T>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    /**
     * 产生节点的高度。使用抛硬币
     *
     * @return 新节点的树高度
     */
    private int getRandomLevel() {
        int lev = 1;
        int val = 2;
        while (random.nextInt() % val == 0) {
            lev++;
        }
        return lev > MAX_LEVEL ? MAX_LEVEL : lev;
    }

    /**
     * 查找跳跃表中的一个值
     *
     * @param score 比较的值
     * @return 返回找到的值
     */
    public T get(double score) {
        SkipNode<T> node = this.get(top, score);
        return node.val;
    }

    /**
     * 递归查询 代码写起来优雅点
     *
     * @param nodeNow 当前节点
     * @param score   存储值
     * @return 找到的值
     */
    private SkipNode<T> get(SkipNode<T> nodeNow, double score, List<SkipNode<T>> path) {
        // 找不到了
        if (nodeNow == null) {
            return null;
        }
        // 当前节点更好找到了
        if (nodeNow.score == score) {
            return nodeNow;
        }
        // 下一个节点没有了或者下一个节点的分数大于当前值 往下一层走
        if (nodeNow.next == null || nodeNow.next.score > score) {
            path.add(nodeNow);
            return get(nodeNow.down, score, path);
        }
        // 其他情况走下一个节点
        return get(nodeNow.next, score, path);
    }

    /**
     * 递归查询 代码写起来优雅点
     *
     * @param nodeNow 当前节点
     * @param score   存储值
     * @return 找到的值
     */
    private SkipNode<T> get(SkipNode<T> nodeNow, double score) {
        // 找不到了
        if (nodeNow == null) {
            return null;
        }
        // 当前节点更好找到了
        if (nodeNow.score == score) {
            return nodeNow;
        }
        // 下一个节点没有了或者下一个节点的分数大于当前值 往下一层走
        if (nodeNow.next == null || nodeNow.next.score > score) {
            return get(nodeNow.down, score);
        }
        // 其他情况走下一个节点
        return get(nodeNow.next, score);
    }

    /**
     * 根据score的值来删除节点。
     *
     * @param score
     */
    public void delete(double score) {
        //1,查找到节点列的第一个节点的前驱
        this.delete(top, score);
    }

    /**
     * 递归删除节点
     *
     * @param nodeNow 当前节点
     * @param score   分数
     */
    private void delete(SkipNode<T> nodeNow, double score) {
        if (nodeNow == null) {
            return;
        }
        // 下个没有 往下层走
        if (nodeNow.next == null) {
            delete(nodeNow.down, score);
            return;
        }
        if (nodeNow.next.score == score) {
            // 在这里说明找到了该删除的节点
            nodeNow.next = nodeNow.next.next;

            //删除当前节点后，还需要继续查找之后需要删除的节点
            delete(nodeNow.down, score);
            return;
        }
        if (nodeNow.next.score > score) {
            delete(nodeNow.down, score);
            return;
        }
        delete(nodeNow.next, score);
    }
}

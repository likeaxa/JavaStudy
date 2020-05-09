package algorithm.skipTable;

import lombok.Data;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 18:03
 */
@Data
public class SkipNode<E> {
    /**
     * 存储的数据
     */
    E val;
    /**
     * 跳跃表按照这个分数值进行从小到大排序
     */
    double score;
    /**
     * next指针
     */
    SkipNode<E> next;
    /**
     * 指向下一层的指针
     */
    SkipNode<E> down;

    public SkipNode(){

    }
    public SkipNode(E val, double score) {
        this.val = val;
        this.score = score;
    }
}

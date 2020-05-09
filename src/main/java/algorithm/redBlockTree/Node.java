package algorithm.redBlockTree;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author by xinjian.yao
 * @date 2020/5/9 14:36
 */

/**
 * 代表红黑叔的一个节点 拥有 节点的颜色，key，value，父节点，左子节点，右子节点
 * @param <K>     节点的k
 * @param <V>节点的v
 */
@Data
public class Node<K, V> implements Serializable, Comparable<Node<K, V>> {

    private int color;
    private K key;
    private V value;
    private Node<K, V> pro;
    private Node<K, V> left;
    private Node<K, V> right;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(key, node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public int compareTo(Node<K, V> o) {
        return this.hashCode() - o.hashCode();
    }
}

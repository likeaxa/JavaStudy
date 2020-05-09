package algorithm.redBlockTree;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author by xinjian.yao
 * @date 2020/5/9 14:41
 */

/**
 * 红黑树性质
 * 1 每个结点要么是红色的要么是黑色的
 * 2 根结点是黑色的
 * 3 红色结点的两个子结点必须是黑色的
 * 4 对于每个结点，从该结点到其所有后代叶结点（最后一个没有分支的子结点）的简单路径上，均包含相同数目的黑色结点
 *
 * @param <K>
 * @param <V>
 */
@Data
public class Tree<K, V> implements Serializable, Iterable<Node<K, V>> {

    private Node<K, V> root;

    private int size;

    private final int BLACK = 0;
    private final int RED = 1;


    @Override
    public void forEach(Consumer<? super Node<K, V>> action) {

    }

    @Override
    public Spliterator<Node<K, V>> spliterator() {
        return null;
    }

    public void put(K key, V value) {
        Node<K, V> z = new Node<>();
        z.setKey(key);
        z.setValue(value);
        z.setColor(RED);
        this.size++;
        // 记录需要插入的父节点
        Node<K, V> y = null;
        Node<K, V> x = root;
        while (x != null) {
            y = x;
            // 新增节点的hashCode 与当前节点的hashCode 比较
            int sp = z.compareTo(x);
            if (sp < 0) {
                x = x.getLeft();
            } else if (sp > 0) {
                x = x.getRight();
            } else {
                // 相等
                x.setValue(value);
                this.size--;
                return;
            }
        }
        z.setPro(y);

        if (y == null) {
            root = z;
        } else if (z.compareTo(y) < 0) {
            y.setLeft(z);
        } else if (z.compareTo(y) > 0) {
            y.setRight(z);
        }


        //调整红黑树
        this.fixup(z);

    }

    private void fixup(Node<K, V> z) {

        // 当前节点的父节点是红色
        while (z.getPro() != null && z.getPro().getColor() == RED) {
            // 当前的节点的爷爷节点必须不为空 不然不需要调整 原先的树是错的了
            // 因为当前节点的爷爷节点是空 那当前节点的父节点就说root节点 root节点不能为红色
            if (z.getPro().getPro() != null) {
                // 当前节点的父节点是爷爷的左节点
                if (z.getPro().equals(z.getPro().getPro().getLeft())) {
                    Node<K, V> y = z.getPro().getPro().getRight();
                    // 判断爷爷节点的右叔叔节点是什么颜色
                    if (y != null && y.getColor() == RED) {
                        // 红色就调整 爸爸和叔叔由红变成黑，爷爷右黑变成红
                        z.getPro().setColor(BLACK);
                        y.setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        z = z.getPro().getPro();
                    } else {
                        // 判断是否需要左旋调整位置
                        if (z.equals(z.getPro().getRight())) {
                            z = z.getPro();
                            this.leftRotate(z);
                        }
                        // 右旋调整树
                        z.getPro().setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        this.rightRotate(z.getPro().getPro());
                    }
                }
                // 反之一样
                else if (z.getPro().equals(z.getPro().getPro().getRight())) {
                    Node<K, V> y = z.getPro().getPro().getLeft();
                    if (y != null && y.getColor() == RED) {
                        z.getPro().setColor(BLACK);
                        y.setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        z = z.getPro().getPro();
                    } else {
                        if (z.equals(z.getPro().getLeft())) {
                            z = z.getPro();
                            this.rightRotate(z);
                        }
                        z.getPro().setColor(BLACK);
                        z.getPro().getPro().setColor(RED);
                        this.leftRotate(z.getPro().getPro());
                    }
                }
            }
        }
        this.root.setColor(BLACK);
    }

    /**
     * 左旋的节点
     * 左旋描述：左旋就是将当前结点（E）移动到左边，
     * 让它的右子结点（S）成为它的父结点并顶替之前它的位置，
     * 然后让它的右子结点的左子结点成为它的新右子结点
     *
     * @param x 节点
     */
    private void leftRotate(Node<K, V> x) {
        Node<K, V> y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setPro(x);
        }
        y.setPro(x.getPro());
        if (x.getPro() == null) {
            this.root = y;
        } else if (x.equals(x.getPro().getLeft())) {
            x.getPro().setLeft(y);
        } else {
            x.getPro().setRight(y);
        }
        y.setLeft(x);
        x.setPro(y);
    }

    /**
     * 这样移动后最担心就是大小顺序问题了，我们看将右子节点（S）上移成为父结点，
     * 因为右子结点是肯定比当前节点（E）大的，换句话说就是E是肯定比S小的，
     * 所以让E成为S的左子结点并没有什么问题，同理S的左子结点也是比E要大的，因为比E小的节点并不会插入到S的子结点上。
     * 左旋搞明白了，右旋就简单了，我们把刚才的操作反向再来一遍就是右旋了。
     *
     * @param x 当前节点
     */
    private void rightRotate(Node<K, V> x) {
        Node<K, V> y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setPro(x);
        }
        y.setPro(x.getPro());
        if (x.getPro() == null) {
            this.root = y;
        } else if (x.equals(x.getPro().getLeft())) {
            x.getPro().setLeft(y);
        } else {
            x.getPro().setRight(y);
        }

        y.setRight(x);
        x.setPro(y);
    }

    /**
     * 中序遍历 看看输出怎么样
     *
     * @param x
     */
    public void inorder(Node<K, V> x) {
        if (x != null) {
            inorder(x.getLeft());
            System.out.println(x.getKey() + ":" + x.getValue());
            inorder(x.getRight());
        }
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Node<K, V>> {

        List<Node<K, V>> array;

        int cur;

        public Iter() {
            cur = 0;
            array = new ArrayList<>();
            Stack<Node<K, V>> stack = new Stack<>();
            Node<K, V> next = root;
            while (true) {
                while (next != null) {
                    stack.push(next);
                    next = next.getLeft();
                }
                if (stack.isEmpty()) break;
                next = stack.pop();
                array.add(next);
                next = next.getRight();
            }
        }

        @Override
        public boolean hasNext() {
            return cur < array.size();
        }

        @Override
        public Node<K, V> next() {
            Node<K, V> node = array.get(cur);
            cur++;
            return node;
        }
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node != null ? node.getValue() : null;
    }

    private Node<K, V> getNode(K key) {
        if (this.root == null)
            return null;
        Node<K, V> x = this.root;
        while (x != null && !x.getKey().equals(key)) {
            if (key.hashCode() - x.getKey().hashCode() < 0) {
                x = x.getLeft();
            } else if (key.hashCode() - x.getKey().hashCode() > 0) {
                x = x.getRight();
            }
        }
        return x;
    }
    public Node<K, V> getMaximum(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getRight();
        }
        return node;
    }
    public Node<K, V> getMinimum(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void remove(K key) {
        Node<K, V> z = getNode(key);
        Node<K, V> y = z;
        Node<K, V> x;
        int oColor = y.getColor();
        if (z.getLeft() == null) {
            x = z.getRight();
            this.RBTransplant(z, z.getRight());
        } else if (z.getRight() == null) {
            x = z.getLeft();
            this.RBTransplant(z, z.getLeft());
        } else {
            y = this.getMinimum(z.getRight());
            oColor = y.getColor();
            x = y.getLeft();
            if (y.getPro().equals(z)) {
                x.setPro(y);
            } else {
                this.RBTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setPro(y);
            }
            this.RBTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setPro(y);
            y.setColor(z.getColor());
        }
        if (oColor == this.BLACK) {
            this.RBRemoveFixup(x);
        }
        this.size--;
    }

    private void RBTransplant(Node<K, V> u, Node<K, V> v) {
        if (u.getPro() == null) {
            this.root = v;
        } else if (u.equals(u.getPro().getLeft())) {
            u.getPro().setLeft(v);
        } else {
            u.getPro().setRight(v);
        }
        if (v != null)
            v.setPro(u.getPro());
    }

    private void RBRemoveFixup(Node<K, V> x) {
        while (x != null && !x.equals(this.root) && x.getColor() == this.BLACK) {
            if (x.equals(x.getPro().getLeft())) {
                Node<K, V> w = x.getPro().getRight();
                if (w.getColor() == this.RED) {
                    w.setColor(this.BLACK);
                    x.getPro().setColor(this.RED);
                    this.leftRotate(x.getPro());
                    w = x.getPro().getRight();
                }

                if (w.getLeft().getColor() == this.BLACK && w.getRight().getColor() == this.BLACK) {
                    w.setColor(RED);
                    x = x.getPro();
                } else if (w.getRight().getColor() == BLACK) {
                    w.getLeft().setColor(BLACK);
                    w.setColor(RED);
                    this.rightRotate(w);
                    w = x.getPro().getRight();
                }

                w.setColor(x.getPro().getColor());
                x.getPro().setColor(BLACK);
                w.getRight().setColor(BLACK);
                this.leftRotate(x.getPro());
                x = root;
            } else {
                Node<K, V> w = x.getPro().getLeft();
                if (w.getColor() == this.RED) {
                    w.setColor(this.BLACK);
                    x.getPro().setColor(this.RED);
                    this.rightRotate(x.getPro());
                    w = x.getPro().getLeft();
                }

                if (w.getRight().getColor() == this.BLACK && w.getLeft().getColor() == this.BLACK) {
                    w.setColor(RED);
                    x = x.getPro();
                } else if (w.getLeft().getColor() == BLACK) {
                    w.getRight().setColor(BLACK);
                    w.setColor(RED);
                    this.leftRotate(x);
                    w = x.getPro().getLeft();
                }

                w.setColor(x.getPro().getColor());
                x.getPro().setColor(BLACK);
                w.getLeft().setColor(BLACK);
                this.rightRotate(x.getPro());
                x = root;
            }
        }
        if (x != null)
            x.setColor(BLACK);
    }

}

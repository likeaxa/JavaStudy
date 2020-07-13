package algorithm.LRU;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author by xinjian.yao
 * @date 2020/7/9 16:38
 */
public class impl2<K, V> {

    // 缓存容量
    int capacity;

    // 缓存容器
    Map<K, NodeCache> cache;

    // 头节点
    NodeCache head;

    // 尾节点
    NodeCache tail;

    public impl2(int capacity) {
        this.capacity = capacity;
//        this.cache = new HashMap<>(capacity);
        this.cache = new Hashtable<>(capacity);
    }

    public void put(K k, V v) {
        NodeCache curNode = cache.get(k);
        // 缓存未命中
        if (curNode == null) {
            // 超过容器容量
            if (cache.size() + 1 > capacity) {
                // 容器删除数据
                cache.remove(tail.k);
                // 删除链表尾节点
                removeLast();
            }

            curNode = new NodeCache(k, v);
        }

        moveToHead(curNode);
        cache.put(k, curNode);
    }

    private void removeLast() {
        if (tail != null) {
            NodeCache temp = tail.pre;
            // 尾节点的前一个节点next引用移除
            if (tail.pre != null) {
                tail.pre.next = null;
            }
            // 尾节点移除pre引用
            tail.pre = null;
            // 尾指针指向前一个节点
            tail = temp;
        }
    }

    /**
     * 把Node位移到头结点
     *
     * @param node
     */
    private void moveToHead(NodeCache node) {
        // node节点有可能是头节点，尾节点，中间节点三种情况，来处理指针删除
        // 情况1,node是头节点
        if (node == head) {
            return;

        }
        // 情况2,node的前一个节点的next引用指向node的后一个节点
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        // node的后一个节点的pre引用指向node的前一个节点
        if (node.next != null){ 
            node.next.pre = node.pre;
        }
        // 情况3,node为尾节点
        if (node == tail){
            tail = node.pre;
        }

        // 位移到头节点
        // 第一次put时（即first==null）不需要处理Node的pre和next两个引用
        if (head != null) {
            node.next = head;
            head.pre = node;
        }
        head = node;
        node.pre = null;
        // 处理第一次put时候last也指向first
        if (tail == null){
            tail = head;
        }
    }

    public V get(K k) {
        NodeCache nodeCache = cache.get(k);
        if (nodeCache != null) {
            return nodeCache.v;
        }
        return null;
    }

    void getAll() {
        for (Map.Entry<K, NodeCache> e : cache.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    class NodeCache {
        K k;
        V v;
        NodeCache pre;
        NodeCache next;

        public NodeCache(K k, V v) {
            super();
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "NodeCache [k=" + k + ", v=" + v + "]";
        }

    }

    public static void main(String[] args) {
        impl2<Integer, Integer> lru = new impl2<Integer, Integer>(10);
        for (int i = 0; i < 17; i++) {
            lru.put(i, i);
        }
        lru.getAll();
    }
}

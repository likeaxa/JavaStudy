package algorithm.LRU;

import sun.misc.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author by xinjian.yao
 * @date 2020/7/9 16:22
 */
public class impl1<K, V> extends LinkedHashMap<K, V> {

    int initSize;

    private impl1(int size) {
        this.initSize = size;

    }
    /**
     * 从写 LinkedHashMap 的 removeEldestEntry 方法 实现最近最少使用
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > impl1.this.initSize;
    }

    private void getAll() {
        for (Map.Entry<K, V> e : this.entrySet()) {
            System.out.println(e.getKey() + ";" + e.getValue());
        }
    }

    public static void main(String[] args) {
        impl1<Integer, Integer> lruCache = new impl1<Integer, Integer>(9);
        for (int i = 1; i <= 20; i++) {
            lruCache.put(i, i);
        }
        lruCache.getAll();
    }

}

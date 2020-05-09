package algorithm.redBlockTree;

/**
 * @author by xinjian.yao
 * @date 2020/5/9 15:25
 */
public class Test {
    public static void main(String[] args) {
        Tree<String, Integer> tree = new Tree<>();
        tree.put("1", 333);
        tree.put("12", 3331);
        tree.put("41", 3313);
        tree.put("21", 3133);
        tree.put("4", 33343);
        tree.put("33", 3353);

//        tree.inorder(tree.getRoot());
        for(Node node : tree){
            System.out.println(node.getKey() + ":" + node.getValue());
        }
        tree.remove("1");
        for(Node node : tree){
            System.out.println(node.getKey() + ":" + node.getValue());
        }
    }
}

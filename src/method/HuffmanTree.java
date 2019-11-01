package method;

import java.util.*;

public class HuffmanTree {
    static class Node implements Comparable<Node>{
        public void setChildren(Node left, Node right) {
            this.left = left;
            this.left.childType = "0";
            this.right = right;
            this.right.childType = "1";
        }

        public Node getLeftChild() {
            return left;
        }

        public Node getRightChild() {
            return right;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(byte value) {
            this.value = value;
        }

        public int getweight() {
            return weight;
        }

        public void setweight(int weight) {
            this.weight = weight;
        }

        public String getChildType() {
            return childType;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        } // 判断是否是叶节点

        @Override
        public int compareTo(Node node) {
            if (this.weight < node.weight)
                return -1;
            else return 1;
        }

        private int value;
        private int weight; // 频数
        private Node left;
        private Node right;
        private String childType = "";  //0代表左节点，1代表右节点

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(int value, int weight) {
            this.left = null;
            this.right = null;
            this.value = value;
            this.weight = weight;

        }

    }

    //按字符出现次数生成优先队列
    //本来用栈做的，不太行有点慢，优先队列很好用
    //配合FileUtil中的weightByte使用
    public PriorityQueue<Node> toQueue(int[] chars) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 0) {
                Node node = new Node(i, chars[i]);
                queue.add(node);
            }
        }
        return queue;
    }

    //生成哈夫曼树
    public Node getHuffmanTree(PriorityQueue<Node> queue) {
        //队列中存在多于两个值
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            //断言右子树不为空，显然不为空
            assert right != null;
            Node parent = new Node(left.weight + right.weight);
            parent.setChildren(left, right);
            queue.add(parent);
        }
        if (queue.size() > 0)
            return queue.poll();
        else return null;
    }

}
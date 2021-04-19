package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Node implements Comparable<Node> {
    public Character ch;
    public int freq;
    public Node left;
    public Node right;
    public boolean isValue;

    public Node(Character ch, boolean isValue) {
        this.ch = ch;
        this.isValue = isValue;
        this.freq = 1;
    }

    public Node(int freq, Node left, Node right, boolean isValue) {
        this.freq = freq;
        this.left = left;
        this.right = right;
        this.isValue = isValue;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(ch!=null?ch.toString():"[]");
        buffer.append('\n');

        if(this.left!=null){
            if (this.right != null) {
                left.print(buffer, childrenPrefix + "├0─ ", childrenPrefix + "│   ");
                right.print(buffer, childrenPrefix + "└1─ ", childrenPrefix + "    ");
            } else {
                left.print(buffer, childrenPrefix + "└0─ ", childrenPrefix + "    ");
            }
        }else{
            if (this.right != null) {
                right.print(buffer, childrenPrefix + "└1─ ", childrenPrefix + "    ");
            }
        }

    }
}

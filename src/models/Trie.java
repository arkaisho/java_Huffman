package models;

import java.util.ArrayList;
import java.util.Collections;

public class Trie {
    public Node root;

    public Trie(ArrayList<Node> nodes) {
        while(nodes.size()>1){
            Node node1 = nodes.remove(0);
            Node node2 = nodes.remove(0);
            nodes.add(new Node(node1.freq+node2.freq,node1,node2,false));
            Collections.sort(nodes);
        }
        root = nodes.get(0);
    }

    public String[] buildCode() {
        String[] st = new String[260];
        buildCode(st, root, "");
        return st;
    }

    public void buildCode(String[] st, Node node, String s) {
        if (node.isLeaf()) {
            st[node.ch] = s;
            return;
        }
        buildCode(st, node.left, s + '0');
        buildCode(st, node.right, s + '1');
    }

    public String toString(){
        return root.toString();
    }
}

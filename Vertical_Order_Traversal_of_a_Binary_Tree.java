package leetcode_question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class Vertical_Order_Traversal_of_a_Binary_Tree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        TreeMap<Integer, List<VerticalPair>> map = new TreeMap<>();
        Queue<VerticalPair> q = new LinkedList<>();
        q.add(new VerticalPair(root, 0, 0));

        while (!q.isEmpty()) {
            VerticalPair vp = q.poll();
            map.putIfAbsent(vp.v, new ArrayList<>());
            map.get(vp.v).add(vp);

            if (vp.node.left != null) {
                q.add(new VerticalPair(vp.node.left, vp.l + 1, vp.v - 1));
            }
            if (vp.node.right != null) {
                q.add(new VerticalPair(vp.node.right, vp.l + 1, vp.v + 1));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int key : map.keySet()) {
            List<VerticalPair> ll = map.get(key);

            // sort by level first, then by value
            Collections.sort(ll, new Comparator<VerticalPair>() {
                public int compare(VerticalPair o1, VerticalPair o2) {
                    if (o1.l == o2.l) {
                        return o1.node.val - o2.node.val;
                    }
                    return o1.l - o2.l;
                }
            });

            List<Integer> list = new ArrayList<>();
            for (VerticalPair v : ll) {
                list.add(v.node.val);
            }
            ans.add(list);
        }
        return ans;
    }

    class VerticalPair {
        TreeNode node;
        int l; // level
        int v; // vertical column

        public VerticalPair(TreeNode node, int l, int v) {
            this.node = node;
            this.l = l;
            this.v = v;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    
    }
    


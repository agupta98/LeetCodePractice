package CodingPractice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TreeNode{
    int val;
    List<TreeNode> children;
    public TreeNode(int val, List<TreeNode> children){
        this.val = val;
        this.children = children;
    }
}

public class KLCA {
    public TreeNode LCAFromKNodes(TreeNode root, List<TreeNode> children){
        Set<TreeNode> set = new HashSet();
        int k = children.size();
        for(TreeNode node: children){
            set.add(node);
        }
        return findKLCA(root, set, k);
    }
    private TreeNode findKLCA(TreeNode root, Set set, int k){
        if(root == null || set.contains(root)){
            return root;
        }
        int count = 0;
        TreeNode res = null;
        for(TreeNode node: root.children){
            TreeNode cur = findKLCA(root, set, k);
            if(cur != null){
                count++;
                res = cur;
            }
        }
        if(count == k){
            return root;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(12 & 11);
      //  System.out.println(palinSubString("abba"));
    }

    public static int palinSubString(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        int count = 0;
        for (int i = 0;i < n; i++) {
            dp[i][i] = 1;
            count++;
        }

        for (int col = 1; col < n; col++) {
            for (int row = 0; row < col;row++) {
                System.out.println(row + " -- " + col);
                if (row == col - 1 && str.charAt(col) == str.charAt(row)) {
                    dp[row][col] = 1;
                    count++;
                }
                else if (dp[row+1][col-1] == 1 && str.charAt(col) == str.charAt(row)) {
                    dp[row][col] = 1;
                    count++;
                }
            }
        }

        return count;
    }
}
import com.sun.source.tree.BinaryTree;
import org.w3c.dom.Node;

import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**Create an empty tree*/
    public Tree(String label) {
        name = label;
    }

    /**Create BST from ArrayList*/
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**Create BST from Array*/
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }


    /**Return a string containing the tree contents as a tree with one node per line*/
    public String toString() {
        System.out.println(name);
        completeString = "";
        return toStringHelper(root, 0);
    }

    String completeString = "";
    private String toStringHelper(BinaryTreeNode node, int level) {
        if (node == null) {
            return "Empty tree";
        }
        toStringHelper(node.right, level + 1);

        for (int i = 0; i < level; i++) {  //Proper indentation according to level
            completeString += "  ";
        }
        if (node.parent != null) {
            completeString += node.key + " [" + node.parent.key + "]\n";
        }
        if (node.parent == null) {
            completeString += node.key + " [no parent]\n";
        }

        toStringHelper(node.left, level + 1);

        return completeString;
    }

    /**Return a string containing the tree contents as a single line*/
    public String inOrderToString() {
        System.out.printf(name + ": ");
        return inOrderToStringHelper(root);
    }

    String inOrderString = "";
    private String inOrderToStringHelper(BinaryTreeNode node) {
        if (node == null) {
            return "";
        }
        inOrderToStringHelper(node.left);
        inOrderString += node.key + " ";
        inOrderToStringHelper(node.right);

        return inOrderString;
    }


    /**reverse left and right children recursively*/
    public void flip() {
        flipHelper(root, 0);
    }

    String indentation = "";
    private void flipHelper(BinaryTreeNode node, int level) {
        if (node == null) {
            return;
        }
        BinaryTreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        for (int i = 0; i < level; i++) {
            indentation += "  ";
        }

        flipHelper(node.left, level + 1);
        flipHelper(node.right, level + 1);
    }

    /**Returns the in-order successor of the specified node*/
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
        return inOrderSuccessorHelper(node);
    }

    private BinaryTreeNode inOrderSuccessorHelper(BinaryTreeNode node) {
        if (node == null) {
            return null; }
        if (node.right != null) {
            return findMin(node.right);
        }
        else {
            if (node.parent.right != node) {
                return node.parent;
            }
            node = node.parent.parent;

            inOrderSuccessorHelper(node.left);
        }
            return node;
    }

    private BinaryTreeNode findMin(BinaryTreeNode nodeToAnalyze) {
        while (nodeToAnalyze.left != null) {
             nodeToAnalyze = nodeToAnalyze.left;
        }
        return nodeToAnalyze;
    }


    /**Counts number of nodes in specified level*/
    public int nodesInLevel(int level) {
        return nodesInLevelHelper(root, 0, level);
    }

    private int nodesInLevelHelper(BinaryTreeNode node, int currLevel, int level) {
        if ((node == null)) {
            return 0;
        }
        if (level == 0) {
            return 1;
        }
        if (currLevel == level) {
            return 1;
        }
        else {
            return nodesInLevelHelper(node.left,currLevel + 1, level) + nodesInLevelHelper(node.right, currLevel + 1, level);
        }
    }

    /**Print all paths from root to leaves*/
    public void printAllPaths() {
        printAllPathsHelper(root);
    }

    ArrayList<E> path = new ArrayList<>();
    private void printAllPathsHelper(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        path.add(node.key);

        if (node.left == null && node.right == null) { //AKA-->IF NODE IS A LEAF NODE
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + " ");
            }
            System.out.println();
        }
        printAllPathsHelper(node.left);
        printAllPathsHelper(node.right);

        path.remove(node.key);
    }


    /**Counts all non-null binary search trees embedded in tree*/
    public int countBST() {
        bstCounter = 0;
        return countBSTHelper(root);
    }

    int bstCounter = 0;   ///Is there a better way to do this than to use a global variable???
    private int countBSTHelper(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            bstCounter++;
            return 0;
        }
        if (((node.left == null) || ((Integer)node.left.key < (Integer)node.key)) && ((node.right == null) || (((Integer)node.right.key > (Integer)node.key)))) {
            bstCounter++;
        }
        countBSTHelper(node.left);
        countBSTHelper(node.right);

        return bstCounter;
    }

    /**Insert into a bst tree; duplicates are allowed*/
    public void insert(E x) {
        root = insert(x, root, null);
    }

    /**Returns a specific bst node*/
    public BinaryTreeNode getByKey(E key) {
        return getByKeyHelper(key, root);
    }

    private BinaryTreeNode getByKeyHelper(E key, BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        }
        else if (key.compareTo(node.key) <= -1){
            return getByKeyHelper(key, node.left);
        }
        else {
            return getByKeyHelper(key, node.right);
        }
    }

    /**Balances the tree*/
    public void balanceTree() {
        ArrayList<BinaryTreeNode> storedInOrderNodes = new ArrayList<>(balanceTreeHelper(root));
        int end = (storedInOrderNodes.size() - 1);
        root = binarySearch(storedInOrderNodes, 0, end, null);
    }

    ArrayList<BinaryTreeNode> inOrderArray = new ArrayList();
    private ArrayList<BinaryTreeNode> balanceTreeHelper(BinaryTreeNode node) {
        if (node == null) {         //Does an in-order traversal and returns an arrayList of Nodes
            return null;
        }

        balanceTreeHelper(node.left);
        inOrderArray.add(node);
        balanceTreeHelper(node.right);

        return inOrderArray;
    }

    public BinaryTreeNode binarySearch(ArrayList<BinaryTreeNode> sortedInOrderNodes, int start, int end, BinaryTreeNode parent) {
        if (start > end) {  //Takes that arrayList of nodes, and does a binary search starting at mid to add all nodes into balanced tree.
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNode node = sortedInOrderNodes.get(mid);

        node.parent = parent;
        node.left = binarySearch(sortedInOrderNodes, start, mid - 1, node);
        node.right = binarySearch(sortedInOrderNodes, mid + 1, end, node);

        return node;
    }

    /**Internal method to insert into a subtree*/
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) return new BinaryTreeNode(x, null, null, parent);

        int compareResult = x.compareTo(t.key);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryTreeNode t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.key);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E key;            // The data/key for the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            key = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(key);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.key);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}

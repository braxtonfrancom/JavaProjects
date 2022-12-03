public class BinarySearchTree<E extends Comparable<E>> {
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;
        public TreeNode(E value) {
            this.value = value;
        }
    }
    private TreeNode root;

    public void displayInOrder() {
        displayInOrder(root);
    }

    public void displayInOrder(TreeNode node) {
        if (node == null) return;
        displayInOrder(node.left);
        System.out.println(node.value);
        displayInOrder(node.right);
    }

    public boolean remove(E value) {
        TreeNode parent = null;
        TreeNode node = root;
        boolean done = false;

        if (!search(value)) {
            return false;
        }

        while (!done) {
            if (node.value.compareTo(value) < 0) {
                parent = node;
                node = node.right;
            } else if (node.value.compareTo(value) > 0) {
                parent = node;
                node = node.left;
            } else {
                done = true;

            }
            }
        // Case for no left child
        if (node.left == null) {
            if (parent == null) {
                root = node.right;
            } else {
                if (parent.value.compareTo(value) < 0) {
                    parent.right = node.right;
                } else {
                    parent.left = node.right;
                }
            }
        } else { // Case for left child
            TreeNode parentOfRight = node;
            TreeNode rightMost = node.left;
            while (rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }
            node.value = rightMost.value;
            if (parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            } else {
                parentOfRight.left = rightMost.left;
            }
        }
        return true;
    }
    public void display(String message) {
        System.out.println(message);
        displayInOrder();
    }
    public boolean insert(E value) {
        if (search(value)) {
            return false;
        }
        if (root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode parent = null;
            TreeNode node = root;
            while (node != null) {
                parent = node;
                if (node.value.compareTo(value) < 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            TreeNode newNode = new TreeNode(value);
            if (parent.value.compareTo(value) < 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
        return true;
    }
    public boolean search(E value) {
        boolean found = false;
        TreeNode node = root;
        while (!found && node != null) {    // Something cool I learned in line 'if (node.value.equals(value))' ///////
                                            // == works for primitives (e.g. int, double, etc.), enums, or whenever you *want* to test object **identity**
            if (node.value.equals(value)) { // always use .equals, never == when comparing strings
                found = true;
            } else if (node.value.compareTo(value) < 0) {
                node = node.right;
            } else {
                node = node.left; }
        }
        return found;
    }

    public int numberNodesHelper(TreeNode node) {
        if (node == null) {
            return 0; }
        return 1 + numberNodesHelper(node.left) + numberNodesHelper(node.right);
    }

    public int numberNodes() {
        return numberNodesHelper(root);
    }

    public int numberLeafNodesHelper(TreeNode node) {
        if (node == null) {
            return 0; }
        if (node.left == null && node.right == null) {
            return 1; }
        return numberLeafNodesHelper(node.left) + numberLeafNodesHelper(node.right);
    }

    public int numberLeafNodes() {
        return numberLeafNodesHelper(root);
    }

    public int heightHelper(TreeNode node) {
        if (node == null) {
            return -1;}
        else {
            return (Math.max(heightHelper(node.left), heightHelper(node.right)) + 1);
        }
    }

    public int height() {
        return heightHelper(root);
    }
}
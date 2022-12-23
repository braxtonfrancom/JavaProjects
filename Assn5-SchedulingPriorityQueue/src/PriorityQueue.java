public class PriorityQueue<E extends Comparable<E>> {

    public Node<E> root;

    private class Node<A extends Comparable<A>> {

        public A value;
        public Node<A> left;
        public Node<A> right;
        public int npl;

        Node(A value) {
            this(value, null, null);
        }

        Node(A value, Node<A> left, Node<A> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            npl = 0;
        }
    }



    private int getNpl(Node<E> node) {
        if (node == null) return -1;

        return node.npl;
    }

    private Node<E> merge(Node<E> t1, Node<E> t2) {
        Node<E> small;
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        if (t1.value.compareTo(t2.value) < 0) {
            t1.right = merge(t1.right, t2);
            small = t1;
        } else {
            t2.right = merge(t2.right, t1);
            small = t2;
        }
        if (getNpl(small.left) < getNpl(small.right))
            swapKids(small);

        setNullPathLength(small);

        return small;
    }

    public void setNullPathLength(Node<E> small) {
        if (small.right == null) {
            small.npl = 0;
        }
        else {
            small.npl = small.right.npl + 1;
        }
    }

    public void swapKids(Node<E> node) {
        Node<E> tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    public void enqueue(E value) {
        root = merge(new Node<>(value), root);
    }

    public E dequeue() {
        if (isEmpty()) {
            return null; }

        E itemToRemove = root.value;
        root = merge(root.left, root.right);
        return itemToRemove;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        else {
            return false;
        }
    }

}
import jdk.swing.interop.SwingInterOpUtils;

public class Queue<E> {

    int count = 0;
    int size = 0;

    public Queue() {}

    private class Node {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

        public Node head = null;
        public Node tail = null;

    @Override
    public String toString() {
        return "" + tail.value;
    }

    public int getCount() {
        return count;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(E value) {
        count++;
        size++;

        Node newNode = new Node(value); //Create a new node

        if (isEmpty()) {  //Checks to see if it is an empty list
            head = newNode;
            tail = newNode;
        }

        tail.next = newNode;  //newNode is added after tail so that tail's next will point to the newNode
        tail = newNode;  //The newNode will become the tail of the list

    }


    public E dequeue() {
        size--;
        if (isEmpty()) {
            System.out.println("The list is empty");
        }

        Node newNode = head; //Store previous front and move front one node ahead
        head = head.next;

        if (head == null) {  //If the front becomes null, then the rear becomes null also
            tail = null;
        }

        return newNode.value;
    }


    public boolean isEmpty() {
        return head == null;
    }

}

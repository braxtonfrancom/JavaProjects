import java.util.Arrays;

public class DisjointSet {
                                    //negative number == size of that particular set
    public int[] array;             //positive number == the parent

    //Constructor
    public DisjointSet(int size) {
        array = new int[size + 1];
        Arrays.fill(array, -1);
    }

    public void union(int root1, int root2) {

        if (root1 == root2) { return; }

        if (array[root2] < array[root1]) {
            array[root2] += array[root1];   // add the size from root1 to root2
            array[root1] = root2;       // Make root2 new root
        }
        else {
            array[root1] += array[root2];   // add the size from root2 to root1
            array[root2] = root1;       // Make root1 new root
        }
    }

    public int find(int node) {
        if (array[node] < 0) {
            return node;
        }
        else {
            int parentOfNode = find(array[node]);
            return array[node] = parentOfNode;
        }
    }
}
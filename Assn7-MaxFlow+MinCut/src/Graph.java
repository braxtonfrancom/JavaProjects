import java.util.*;

public class Graph {
    private final String name;  //The file from which the graph was created.

    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final GraphNode[] residualGraph; //Tracks residual flow of the graph

    public Graph(String name, int vertexCount) {
        this.name = name;
        vertices = new GraphNode[vertexCount];
        residualGraph = new GraphNode[vertexCount];

        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
            residualGraph[vertex] = new GraphNode(vertex);
        }
    }

    public boolean addEdge(int source, int destination, int capacity) {
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        vertices[source].addEdge(source, destination, capacity); //Forward flow edge
        vertices[destination].addEdge(destination, source, 0); //Backward flow edge

        residualGraph[source].addEdge(source, destination, capacity);
        residualGraph[destination].addEdge(destination, source, 0);

        return true;
    }

    /** Algorithm to find max-flow in a network*/
    public int findMaxFlow(int s, int t, boolean report) {
        int totalFlow = 0;
        System.out.println("-- Max Flow: " + name + " --");
        while (hasAugmentingPath(s, t)) {
            int availableFlow = Integer.MAX_VALUE;

            GraphNode current = residualGraph[t];
            while (current.parent != -1) {
                GraphNode prevNode = current;  // current is the to node  //current.parent is the from node --> SIMPLIFY
                current = residualGraph[current.parent];
                for (int i = 0; i < current.successor.size(); i++) {
                    if (current.successor.get(i).to == prevNode.id) {
                        if (current.successor.get(i).capacity < availableFlow) {
                            availableFlow = current.successor.get(i).capacity;
                        }
                    }
                }
            } //HERE--> I have the largest possible flow for the augmenting path we are looking at

            ArrayList<Integer> arrayOfPath = new ArrayList<>();
            current = residualGraph[t];
            arrayOfPath.add(current.id);

            while (current.parent != -1) {
                GraphNode prevNode = current;
                current = residualGraph[current.parent];
                for (int i = 0; i < current.successor.size(); i++) {
                    if (current.successor.get(i).to == prevNode.id) {
                        current.successor.get(i).capacity -= availableFlow;  //subtracting the capacity along that edge
                        }
                    }
                for (int j = 0; j < prevNode.successor.size(); j++) { //Handling backwards flow
                    if (prevNode.successor.get(j).to == current.id) {
                        prevNode.successor.get(j).capacity += availableFlow;
                        arrayOfPath.add(current.id);
                    }
                }
            }
            System.out.print("Flow " + availableFlow + ": ");
            for (int i = arrayOfPath.size() - 1; i >= 0; i--) {
                System.out.print(arrayOfPath.get(i) + " ");
            }
            System.out.println();
            totalFlow += availableFlow;
        }

        if (report) {
            System.out.println();
            for (int i = 0; i < residualGraph.length; i++) {
                for (int k = 0; k < residualGraph[i].successor.size(); k++) {
                    if (vertices[i].successor.get(k).capacity != 0 && vertices[i].successor.get(k).capacity != residualGraph[i].successor.get(k).capacity) {
                        System.out.println("Edge(" + residualGraph[i].successor.get(k).from + ", " + residualGraph[i].successor.get(k).to + ") transports " + (vertices[i].successor.get(k).capacity - residualGraph[i].successor.get(k).capacity) + " items");
                    }
                }
            }
        }
        return totalFlow;
    }

    /** Algorithm to find an augmenting path in a network*/
    private boolean hasAugmentingPath(int s, int t) {  // Uses shortest path algorithm to find the best path
        Queue<Integer> myQueue = new LinkedList<>();

        for (int i = 0; i < residualGraph.length; i++) {
            residualGraph[i].parent = -1;
        }

        myQueue.add(s);
        while (!myQueue.isEmpty() && residualGraph[t].parent == -1) {
            int v = myQueue.remove();

            for (int edge = 0; edge < residualGraph[v].successor.size(); edge++) {
                int w = residualGraph[v].successor.get(edge).to;
                int capacity = residualGraph[v].successor.get(edge).capacity;
                if (capacity > 0 && residualGraph[w].parent == -1 && w != s) {
                    residualGraph[w].parent = v;
                    myQueue.add(w);
                }
            }
            if (residualGraph[t].parent != -1) {  //If vertex t has a parent --> there is an augmenting path from s to t
                return true;
            }
        }
        return false;
    }


    /** Algorithm to find the min-cut edges in a network*/
    public void findMinCut(int s) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Queue<Integer> myQueue = new LinkedList<>();
        System.out.println("\n-- Min Cut: " + name + " --");

        myQueue.add(s);
        arrayList.add(s);

        while (!myQueue.isEmpty()) {
            int v = myQueue.remove();
            for (int i = 0; i < residualGraph[v].successor.size(); i++) {
                int nextVertex = residualGraph[v].successor.get(i).to;
                if (residualGraph[v].successor.get(i).capacity > 0 && !residualGraph[nextVertex].visited) {
                    arrayList.add(nextVertex);
                    myQueue.add(nextVertex);
                    residualGraph[nextVertex].visited = true;
                }
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            int v = arrayList.get(i);
            for (int j = 0; j < residualGraph[v].successor.size(); j++) {
                int nextSpot = residualGraph[v].successor.get(j).to;
                if (!arrayList.contains(nextSpot)) {
                    System.out.println("Min Cut Edge: (" + v + ", " + residualGraph[v].successor.get(j).to + ")" + " : "+ vertices[v].successor.get(j).capacity);
                }
            }
        }
        System.out.println();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("The Graph " + name + " \n");
        for (var vertex : vertices) {
            sb.append((vertex.toString()));
        }
        return sb.toString();
    }
}
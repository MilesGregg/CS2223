package mgregg;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

import java.io.FileNotFoundException;

public class Testing {
    boolean marked[];
    int edgeTo[];
    final Graph g;
    public Testing (Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.g = g;
        bfs(g, s);
    }

    /** Initiate BFS search over graph at s. */
    void bfs (Graph g, int s) {
        for (int v = 0; v < g.V(); v++) {
            marked[v] = false;
        }
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            System.out.println("at this: " + v);
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    /*if (w == 5) {
                        System.out.println("Size: " + q.size());
                        while (!q.isEmpty()) {
                            System.out.println(q.dequeue());
                        }
                        break;
                    }*/
                    q.enqueue(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(2, 1);
        graph.addEdge(3, 1);
        graph.addEdge(3, 5);
        graph.addEdge(5, 6);

        Testing test = new Testing(graph, 0);
    }

}

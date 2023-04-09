package org.christophides;

import org.structs.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MSTGenerator {

    public List<Edge>  calculateMST(double[][]distanceMatrix) {

        List<Edge> mst = generateMST(distanceMatrix);
        double totDistance = 0;
        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mst) {
            //System.out.println(edge.getSrc() + " - " + edge.getDest());
            totDistance=totDistance+distanceMatrix[edge.getSrc()][edge.getDest()];
        }
        System.out.println("Total distance covered: "+totDistance);
        return mst;
    }
    public static List<Edge> generateMST(double[][] distanceMatrix) {
        int numVertices = distanceMatrix.length;
        boolean[] visited = new boolean[numVertices];
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(distanceMatrix[a.getSrc()][a.getDest()], distanceMatrix[b.getSrc()][b.getDest()]));

        // Start with the first vertex as the root
        int root = 0;
        visited[root] = true;
        for (int dest = 0; dest < numVertices; dest++) {
            if (dest != root) {
                pq.offer(new Edge(root, dest,0));
            }
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int src = edge.getSrc();
            int dest = edge.getDest();
            if (!visited[dest]) {
                visited[dest] = true;
                mst.add(edge);
                for (int i = 0; i < numVertices; i++) {
                    if (!visited[i]) {
                        pq.offer(new Edge(dest, i,0));
                    }
                }
            }
        }

        return mst;
    }


}

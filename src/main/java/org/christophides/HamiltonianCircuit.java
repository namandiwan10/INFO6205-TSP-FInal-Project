package org.christophides;
import org.structs.Edge;
import org.structs.Node;
import org.util.DistanceCalculator;
import org.util.ParseCSV;

import java.util.*;

public class HamiltonianCircuit {

    public List<Integer> findHamiltonianCircuit(List<Edge> eulerianGraph, double[][] distanceMatrix) {
        DistanceCalculator dc = new DistanceCalculator();
        Node node=new Node();
        ParseCSV pc=new ParseCSV();
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        List<Integer> hamiltonianCircuit = new ArrayList<>();

        // Create adjacency list from Eulerian graph
        for (Edge edge : eulerianGraph) {
            int src = edge.getSrc();
            int dest = edge.getDest();

            if (!adjacencyList.containsKey(src)) {
                adjacencyList.put(src, new ArrayList<>());
            }
            if (!adjacencyList.containsKey(dest)) {
                adjacencyList.put(dest, new ArrayList<>());
            }

            adjacencyList.get(src).add(dest);
            adjacencyList.get(dest).add(src);
        }

        // Start at any vertex in the Eulerian graph
        int startVertex = eulerianGraph.get(0).getSrc();
        traverseEulerianGraph(startVertex, adjacencyList, hamiltonianCircuit);

        System.out.println("TSP Path => ");//
        double totDistance = 0;
        for (int i : hamiltonianCircuit) {
            //System.out.println(i);
            System.out.print(pc.nodeList.get(i).getId().substring(pc.nodeList.get(i).getId().length() - 5));
            System.out.print(",");
        }

        double totalDistance = 0;
        int n = hamiltonianCircuit.size();

        for (int i = 0; i < n; i++) {
            int src = hamiltonianCircuit.get(i);
            int dest = hamiltonianCircuit.get((i + 1) % n); // wrap around to the first vertex for last edge
            totDistance += getDistance(src, dest, distanceMatrix);
        }
        System.out.println("\nTotal distance covered: " + totDistance);
        return hamiltonianCircuit;
    }

    private void traverseEulerianGraph(int vertex, Map<Integer, List<Integer>> adjacencyList, List<Integer> hamiltonianCircuit) {
        while (!adjacencyList.get(vertex).isEmpty()) {
            int nextVertex = adjacencyList.get(vertex).remove(0);
            adjacencyList.get(nextVertex).remove((Integer) vertex);
            traverseEulerianGraph(nextVertex, adjacencyList, hamiltonianCircuit);
        }

        hamiltonianCircuit.add(vertex);
    }

    private double getDistance(int src, int dest, double[][] distanceMatrix) {
        return distanceMatrix[src][dest];
    }


}

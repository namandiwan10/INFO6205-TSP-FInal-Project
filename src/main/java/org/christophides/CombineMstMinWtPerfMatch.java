package org.christophides;

import org.structs.Edge;

import java.util.ArrayList;
import java.util.List;

public class CombineMstMinWtPerfMatch {


    public List<Edge> combineMSTAndPerfectMatching(List<Edge> mst, List<Edge> minWtPerfMatch) {

        List<Edge> combinedGraph = new ArrayList<>();

        // Add edges from MST to combinedGraph
        for (Edge edge : mst) {
            combinedGraph.add(edge);
        }

        // Add edges from perfectMatching to combinedGraph
        for (Edge edge : minWtPerfMatch) {
            combinedGraph.add(edge);
        }

        //System.out.println("Combined graph Edges:");
        double totDistance = 0;
        for (Edge edge : combinedGraph) {
            //System.out.println(edge.getSrc() + " - " + edge.getDest());
            totDistance += edge.getWeight();
        }
       // System.out.println("Total distance covered: " + totDistance);
        return combinedGraph;
    }
}

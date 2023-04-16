package org.christophides;
import org.christophides.HamiltonianCircuit;
import org.junit.Assert;
import org.junit.Test;
import org.structs.Edge;

import java.util.*;

public class HamiltonianCircuitTest {
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindHamiltonianCircuit() {
        // Create a sample graph with 5 vertices
        List<Edge> eulerianGraph = new ArrayList<>();
        eulerianGraph.add(new Edge(0, 1, 2));
        eulerianGraph.add(new Edge(1, 2, 3));
        eulerianGraph.add(new Edge(2, 3, 4));
        eulerianGraph.add(new Edge(3, 4, 5));
        eulerianGraph.add(new Edge(4, 0, 1));
        double[][] distanceMatrix = {
                {0, 2, 0, 0, 1},
                {2, 0, 3, 0, 0},
                {0, 3, 0, 4, 0},
                {0, 0, 4, 0, 5},
                {1, 0, 0, 5, 0}
        };
        
        HamiltonianCircuit hc = new HamiltonianCircuit();
        List<Integer> circuit = hc.findHamiltonianCircuit(eulerianGraph, distanceMatrix);
        
        // Check that the circuit has the correct length
        Assert.assertEquals(6, circuit.size());
        
        // Check that the circuit is valid (i.e., it is a Hamiltonian circuit)
        Set<Integer> visitedVertices = new HashSet<>();
        visitedVertices.add(circuit.get(0));
        for (int i = 1; i < circuit.size(); i++) {
            int currVertex = circuit.get(i);
            int prevVertex = circuit.get(i-1);
            visitedVertices.add(currVertex);
            Assert.assertTrue(distanceMatrix[prevVertex][currVertex] > 0); // edge exists
        }
        Assert.assertEquals(5, visitedVertices.size()); // all vertices visited exactly once
        Assert.assertFalse(distanceMatrix[circuit.get(0)][circuit.get(circuit.size()-1)] > 0); // edge from last vertex to first vertex exists
        
        // Check that the total distance covered is correct
        double totalDistance = 0;
        for (int i = 0; i < circuit.size()-1; i++) {
            int src = circuit.get(i);
            int dest = circuit.get(i+1);
            totalDistance += distanceMatrix[src][dest];
        }
        Assert.assertEquals(15, totalDistance, 0.001);
    }
    
}

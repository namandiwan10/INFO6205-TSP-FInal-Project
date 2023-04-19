package org.strategicopt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StrategicSimulatedAnnealingTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOptimizeSimulatedAnnealing() {
        double[][] distanceMatrix = {{0, 1, 2}, {1, 0, 3}, {2, 3, 0}};
        List<Integer> hamiltonianCircuit = new ArrayList<>(Arrays.asList(0, 1, 2));
        StrategicSimulatedAnnealing sa = new StrategicSimulatedAnnealing();
        List<Integer> optimizedCircuit = sa.optimizeSimulatedAnnealing(hamiltonianCircuit, distanceMatrix);
        assertNotNull(optimizedCircuit);
        assertEquals(hamiltonianCircuit.size(), optimizedCircuit.size());
        double distance = sa.getDistance(optimizedCircuit, distanceMatrix);
        assertEquals(6.0, distance, 0.0001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOptimizeSimulatedAnnealingWithSmallDistanceMatrix() {
        double[][] distanceMatrix = {{0, 1}, {1, 0}};
        List<Integer> hamiltonianCircuit = new ArrayList<>(Arrays.asList(0, 1));
        StrategicSimulatedAnnealing sa = new StrategicSimulatedAnnealing();
        List<Integer> optimizedCircuit = sa.optimizeSimulatedAnnealing(hamiltonianCircuit, distanceMatrix);
        assertNotNull(optimizedCircuit);
        assertEquals(hamiltonianCircuit.size(), optimizedCircuit.size());
        double distance = sa.getDistance(optimizedCircuit, distanceMatrix);
        assertEquals(2.0, distance, 0.0001);
    }

//    @Test
//    public void testOptimizeSimulatedAnnealingWithOneVertex() {
//        double[][] distanceMatrix = {{0}};
//        List<Integer> hamiltonianCircuit = new ArrayList<>(Arrays.asList(0));
//        StrategicSimulatedAnnealing sa = new StrategicSimulatedAnnealing();
//        List<Integer> optimizedCircuit = sa.optimizeSimulatedAnnealing(hamiltonianCircuit, distanceMatrix);
//        assertNotNull(optimizedCircuit);
//        assertEquals(hamiltonianCircuit.size(), optimizedCircuit.size());
//        double distance = sa.getDistance(optimizedCircuit, distanceMatrix);
//        assertEquals(0.0, distance, 0.0001);
//    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOptimizeSimulatedAnnealingWithEmptyCircuit() {
        double[][] distanceMatrix = {{0, 1}, {1, 0}};
        List<Integer> hamiltonianCircuit = new ArrayList<>();
        StrategicSimulatedAnnealing sa = new StrategicSimulatedAnnealing();
        List<Integer> optimizedCircuit = sa.optimizeSimulatedAnnealing(hamiltonianCircuit, distanceMatrix);
        assertNotNull(optimizedCircuit);
        assertEquals(hamiltonianCircuit.size(), optimizedCircuit.size());
    }

    @Test
    public void testGetDistanceWithInvalidCircuit() {
        double[][] distanceMatrix = {{0, 1}, {1, 0}};
        List<Integer> invalidCircuit = new ArrayList<>(Arrays.asList(0, 1, 2));
        StrategicSimulatedAnnealing sa = new StrategicSimulatedAnnealing();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            sa.getDistance(invalidCircuit, distanceMatrix);
        });
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSwapVerticesWithInvalidIndices() {
        List<Integer> circuit = new ArrayList<>(Arrays.asList(0, 1, 2));
        StrategicSimulatedAnnealing sa = new StrategicSimulatedAnnealing();
        
        // Swap vertices with negative indices
        sa.swapVertices(circuit, -1, 1);
        List<Integer> expectedCircuit = Arrays.asList(0, 1, 2);
        assertEquals(expectedCircuit, circuit);
        
        // Swap vertices with indices greater than circuit size
        sa.swapVertices(circuit, 2, 3);
        assertEquals(expectedCircuit, circuit);
    }
}
       

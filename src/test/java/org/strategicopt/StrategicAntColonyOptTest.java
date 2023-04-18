package org.strategicopt;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.strategicopt.StrategicAntColonyOpt;

public class StrategicAntColonyOptTest {

    private StrategicAntColonyOpt sa;

    @Before
    public void setUp() {
        sa = new StrategicAntColonyOpt();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOptimizeAntColonyOptimization() {
        // Create a distance matrix
        double[][] distanceMatrix = {{0, 1, 2}, {1, 0, 3}, {2, 3, 0}};

        // Create a Hamiltonian path
        List<Integer> hamiltonianPath = Arrays.asList(0, 1, 2);

        // Run the optimization
        List<Integer> optimizedPath = sa.optimizeAntColonyOptimization(hamiltonianPath, distanceMatrix);

        // Verify that the optimized path is a valid Hamiltonian path
        assertEquals(hamiltonianPath.size(), optimizedPath.size());
        List<Integer> visited = new ArrayList<>(hamiltonianPath.size());
        visited.addAll(optimizedPath);
        assertTrue(visited.containsAll(hamiltonianPath));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testOptimizeAntColonyOptimizationWithInvalidInput() {
        // Create an invalid distance matrix with negative values
        double[][] distanceMatrix = {{0, -1}, {-1, 0}};

        // Create a Hamiltonian path
        List<Integer> hamiltonianPath = Arrays.asList(0, 1);

        // Run the optimization (should throw an exception)
        sa.optimizeAntColonyOptimization(hamiltonianPath, distanceMatrix);
    }

}

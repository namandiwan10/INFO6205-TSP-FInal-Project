package org.tacticalopt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TacticalRandomSwapTest {

	@Test(expected = IndexOutOfBoundsException.class)
	public void testOptimizeRandomSwap() {
		TacticalRandomSwap trs=new TacticalRandomSwap();
		// Create a small distance matrix for testing
		double[][] distanceMatrix = { { 0, 1, 2 }, { 1, 0, 3 }, { 2, 3, 0 } };

		// Create a list representing a Hamiltonian circuit
		List<Integer> hamiltonianCircuit = Arrays.asList(0, 1, 2);

		// Test the method with a single swap
	    List<Integer> optimizedCircuit = trs.optimizeRandomSwap(hamiltonianCircuit, distanceMatrix, 1);
	    assertEquals(optimizedCircuit, Arrays.asList(0, 1, 2));

		// Test the method with no swaps
		optimizedCircuit = trs.optimizeRandomSwap(hamiltonianCircuit, distanceMatrix, 0);
		assertEquals(optimizedCircuit, Arrays.asList(0, 1, 2));

		// Test the method with more swaps than the size of the circuit
		optimizedCircuit = trs.optimizeRandomSwap(hamiltonianCircuit, distanceMatrix, 5);
		assertEquals(optimizedCircuit.size(), hamiltonianCircuit.size());

		// Test the method with a large distance matrix and circuit
		distanceMatrix = new double[100][100];
		for (int i = 0; i < distanceMatrix.length; i++) {
			for (int j = 0; j < distanceMatrix[i].length; j++) {
				distanceMatrix[i][j] = Math.random() * 10;
			}
		}
		hamiltonianCircuit = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			hamiltonianCircuit.add(i);
		}
		optimizedCircuit = trs.optimizeRandomSwap(hamiltonianCircuit, distanceMatrix, 10);
		assertNotNull(optimizedCircuit);
	}
}

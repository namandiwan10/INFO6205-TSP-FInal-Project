package org.strategicopt;
import org.util.ParseCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategicSimulatedAnnealing {

    private static final double INITIAL_TEMPERATURE = 1000.0;
    private static final double COOLING_RATE = 0.99;
    private static final int NUM_SWAPS_PER_TEMPERATURE = 100000;

    public List<Integer> optimizeSimulatedAnnealing(List<Integer> hamiltonianCircuit, double[][] distanceMatrix) {
        Random random = new Random();
        ParseCSV pc=new ParseCSV();
        List<Integer> localHamiltonianList = new ArrayList<>();
        hamiltonianCircuit.forEach(e->{
            localHamiltonianList.add(e);
        });
        double temperature = INITIAL_TEMPERATURE;

        // Calculate the initial distance of the circuit
        double currentDistance = getDistance(localHamiltonianList, distanceMatrix);

        while (temperature > 0.1) {
            for (int i = 0; i < NUM_SWAPS_PER_TEMPERATURE; i++) {
                // Generate two random indices to swap
                int index1 = random.nextInt(localHamiltonianList.size());
                int index2 = random.nextInt(localHamiltonianList.size());
                while (index2 == index1) {
                    index2 = random.nextInt(localHamiltonianList.size());
                }

                // Swap the vertices
                swapVertices(localHamiltonianList, index1, index2);

                // Calculate the new distance after the swap
                double newDistance = getDistance(localHamiltonianList, distanceMatrix);

                // Calculate the change in distance
                double delta = newDistance - currentDistance;

                // If the new distance is better, accept the swap
                if (delta < 0) {
                    currentDistance = newDistance;
                }
                // If the new distance is worse, accept the swap with a certain probability
                else {
                    double acceptanceProbability = Math.exp(-delta / temperature);
                    if (random.nextDouble() < acceptanceProbability) {
                        currentDistance = newDistance;
                    } else {
                        // Undo the swap
                        swapVertices(localHamiltonianList, index1, index2);
                    }
                }
            }

            // Cool down the temperature
            temperature *= COOLING_RATE;
        }

        System.out.println("TSP after simulated annealing:");
        double totalDistance = 0.0;
        for (int i = 0; i < localHamiltonianList.size(); i++) {
            int src = localHamiltonianList.get(i);
            int dest = localHamiltonianList.get((i + 1) % localHamiltonianList.size());
            //System.out.println(src + " - " + dest );
            totalDistance += distanceMatrix[src][dest];
        }

        for (int i : localHamiltonianList) {
            //System.out.println(i);
            System.out.print(pc.nodeList.get(i).getId().substring(pc.nodeList.get(i).getId().length() - 5));
            System.out.print(",");
        }



        System.out.println("\nTotal distance traveled: " + totalDistance);
        return localHamiltonianList;
    }

    public double getDistance(List<Integer> circuit, double[][] distanceMatrix) {
        double totalDistance = 0.0;
        for (int i = 0; i < circuit.size() - 1; i++) {
            totalDistance += distanceMatrix[circuit.get(i)][circuit.get(i + 1)];
        }
        totalDistance += distanceMatrix[circuit.get(circuit.size() - 1)][circuit.get(0)];
        return totalDistance;
    }

    public void swapVertices(List<Integer> circuit, int index1, int index2) {
        int temp = circuit.get(index1);
        circuit.set(index1, circuit.get(index2));
        circuit.set(index2, temp);
    }
}

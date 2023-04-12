package org.strategicopt;

import org.util.ParseCSV;

import java.util.ArrayList;
import java.util.List;

public class StrategicAntColonyOpt {
    private static final int NUM_ANTS = 10; // Number of ants
    private static final double ALPHA = 1.0; // Pheromone importance factor
    private static final double BETA = 2.0; // Heuristic information importance factor
    private static final double RHO = 0.1; // Pheromone evaporation rate
    private static final double Q = 1.0; // Pheromone deposit amount
    private static final int MAX_ITERATIONS = 100; // Maximum number of iterations

    public List<Integer> optimizeAntColonyOptimization(List<Integer> hamiltonianPath, double[][] distanceMatrix) {
        ParseCSV pc=new ParseCSV();
        // Initialization
        int numNodes = distanceMatrix.length;
        double[][] pheromoneMatrix = new double[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                pheromoneMatrix[i][j] = 1.0; // Initialize pheromone matrix with a constant value
            }
        }

        List<Integer> bestSolution = new ArrayList<>();
        double bestSolutionCost = Double.MAX_VALUE;

        // Ant Colony Optimization loop
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            List<List<Integer>> antPaths = new ArrayList<>();
            List<Double> antPathCosts = new ArrayList<>();

            // Construct ant paths
            for (int ant = 0; ant < NUM_ANTS; ant++) {
                List<Integer> antPath = new ArrayList<>();
                boolean[] visited = new boolean[numNodes];
                int startNode = hamiltonianPath.get(0);
                antPath.add(startNode);
                visited[startNode] = true;

                for (int i = 1; i < numNodes; i++) {
                    int currentNode = antPath.get(i - 1);
                    int nextNode = -1;
                    double maxPheromone = 0.0;

                    for (int j = 0; j < numNodes; j++) {
                        if (!visited[j]) {
                            double pheromone = pheromoneMatrix[currentNode][j];
                            double heuristic = 1.0 / distanceMatrix[currentNode][j];
                            double value = Math.pow(pheromone, ALPHA) * Math.pow(heuristic, BETA);

                            if (value > maxPheromone) {
                                maxPheromone = value;
                                nextNode = j;
                            }
                        }
                    }

                    antPath.add(nextNode);
                    visited[nextNode] = true;
                }

                antPaths.add(antPath);
                antPathCosts.add(calculatePathCost(antPath, distanceMatrix));
            }

            // Update pheromone matrix
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    pheromoneMatrix[i][j] *= (1 - RHO); // Evaporate pheromone
                }
            }

            for (int ant = 0; ant < NUM_ANTS; ant++) {
                List<Integer> antPath = antPaths.get(ant);

                double pheromoneDeposit = Q / antPathCosts.get(ant); // Calculate pheromone deposit amount

                // Deposit pheromone on ant path
                for (int i = 0; i < numNodes - 1; i++) {
                    int currentNode = antPath.get(i);
                    int nextNode = antPath.get(i + 1);
                    pheromoneMatrix[currentNode][nextNode] += pheromoneDeposit;
                    pheromoneMatrix[nextNode][currentNode] += pheromoneDeposit; // Update both directions of the pheromone matrix
                }

                // Update best solution
                if (antPathCosts.get(ant) < bestSolutionCost) {
                    bestSolution = antPath;
                    bestSolutionCost = antPathCosts.get(ant);
                }
            }
        }

        System.out.println("TSP Path after Ant Colony opt=> ");//
        double totDistance = 0;
        for (int i : bestSolution) {
            //System.out.println(i);
            System.out.print(pc.nodeList.get(i).getId().substring(pc.nodeList.get(i).getId().length() - 5));
            System.out.print(",");
        }
        System.out.println("\nTotal distance: "+bestSolutionCost);
        return bestSolution;
    }

    // Helper method to calculate the cost of a path given a distance matrix
    private double calculatePathCost(List<Integer> path, double[][] distanceMatrix) {
        double cost = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            int currentNode = path.get(i);
            int nextNode = path.get(i + 1);
            cost += distanceMatrix[currentNode][nextNode];
        }
        return cost;
    }
}



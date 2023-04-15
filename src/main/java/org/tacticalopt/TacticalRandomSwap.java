package org.tacticalopt;

import org.util.ParseCSV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TacticalRandomSwap {

    public List<Integer> optimizeRandomSwap(List<Integer> hamiltonianPath, double[][] distanceMatrix, int iterations) {
        ParseCSV pc=new ParseCSV();
        List<Integer> optimizedPath = new ArrayList<>(hamiltonianPath);
        double bestDistance = calculateTotalDistance(optimizedPath, distanceMatrix);

        Random random = new Random();

        for (int i = 0; i < iterations; i++) {
            List<Integer> newPath = new ArrayList<>(optimizedPath);
            int index1 = random.nextInt(newPath.size() - 1) + 1;
            int index2 = random.nextInt(newPath.size() - 1) + 1;

            Collections.swap(newPath, index1, index2);

            double newDistance = calculateTotalDistance(newPath, distanceMatrix);

            if (newDistance < bestDistance) {
                //System.out.println("heree.....");
                optimizedPath = newPath;
                bestDistance = newDistance;
            }
        }

        System.out.println("Points after Random Swap opt => ");//
        //double totDistance = 0;
        for (int i : optimizedPath) {
            //System.out.println(i);
            System.out.print(pc.nodeList.get(i).getId().substring(pc.nodeList.get(i).getId().length() - 5));
            System.out.print(",");
        }

        System.out.println("\nDistance after random swap: "+calculateTotalDistance(optimizedPath, distanceMatrix));
        return optimizedPath;
    }

    private double calculateTotalDistance(List<Integer> path, double[][] distanceMatrix) {
        double totalDistance = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += distanceMatrix[path.get(i)][path.get(i + 1)];
        }

        totalDistance += distanceMatrix[path.get(path.size() - 1)][path.get(0)];

        return totalDistance;
    }
}
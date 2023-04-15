package org.tacticalopt;
import org.util.ParseCSV;

import java.util.*;

public class TacticalTwoOpt {

    public List<Integer> twoOpt(List<Integer> hamiltonianCircuit, double[][] distanceMatrix) {
        ParseCSV pc=new ParseCSV();
        List<Integer> localHamiltonianList = new ArrayList<Integer>();
        hamiltonianCircuit.forEach(e->{
            localHamiltonianList.add(e);
        });

        int n = localHamiltonianList.size();
         boolean improved = true;

        while (improved) {
            improved = false;

            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 2; j < n; j++) {
                    double currentDistance = getDistance(localHamiltonianList.get(i), localHamiltonianList.get(i + 1), distanceMatrix)
                            + getDistance(localHamiltonianList.get(j), localHamiltonianList.get((j + 1) % n), distanceMatrix);
                    double newDistance = getDistance(localHamiltonianList.get(i), localHamiltonianList.get(j), distanceMatrix)
                            + getDistance(localHamiltonianList.get(i + 1), localHamiltonianList.get((j + 1) % n), distanceMatrix);

                    if (newDistance < currentDistance) {
                        reverseSublist(localHamiltonianList, i + 1, j);
                        improved = true;
                    }
                }
            }
        }

        System.out.println("Points after 2 opt => ");//
        //double totDistance = 0;
        for (int i : hamiltonianCircuit) {
            //System.out.println(i);
            System.out.print(pc.nodeList.get(i).getId().substring(pc.nodeList.get(i).getId().length() - 5));
            System.out.print(",");
        }
        // System.out.println("Total distance covered: " + totDistance);

        double totDistance = 0;
        for (int i = 0; i < n; i++) {
            int src = localHamiltonianList.get(i);
            int dest = localHamiltonianList.get((i + 1) % n); // wrap around to the first vertex for last edge
            totDistance += getDistance(src, dest, distanceMatrix);
        }
        System.out.println("\nTotal distance covered: " + totDistance);
        //System.out.println("equal : " + localHamiltonianList.equals(hamiltonianCircuit));
        return localHamiltonianList;
    }

    private double getDistance(int src, int dest, double[][] distanceMatrix) {
        return distanceMatrix[src][dest];
    }

    public static void reverseSublist(List<Integer> list, int start, int end) {
        while (start < end) {
            int temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}

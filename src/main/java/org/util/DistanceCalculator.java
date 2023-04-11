package org.util;

import org.structs.Node;

import java.util.List;

public class DistanceCalculator {
    public double[][] calculateDistance(List<Node> nodeList) {
        double[][] distanceMatrix = new double[nodeList.size()][nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                distanceMatrix[i][j] = distance(nodeList.get(i), nodeList.get(j));
            }

        }

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                double distance = distanceMatrix[i][j];
                // Do something with the distance value
                //System.out.println("Distance between Node " + nodeList.get(i).getId() + " and Node " + nodeList.get(j).getId() + ": " + distance);
                //System.out.println("Distance between Node " + i + " and Node " + j + ": " + distance);

            }
        }
        return distanceMatrix;

    }

    public static double distance(Node node1, Node node2) {
        double lon1 = Math.toRadians(node1.getLongitude());
        double lat1 = Math.toRadians(node1.getLatitude());
        double lon2 = Math.toRadians(node2.getLongitude());
        double lat2 = Math.toRadians(node2.getLatitude());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.sin(dlat/2) * Math.sin(dlat/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon/2) * Math.sin(dlon/2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of the earth in kilometers
        double radius = 6371;

        double distance = (c * radius)*1000;

        return distance;
    }
}


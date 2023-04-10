    package org.christophides;

    import org.structs.Edge;

    import java.util.*;


    public class MinWtPerfMatch {
        private double[][] distanceMatrix;

        public List<Edge> calculateMST(double[][] distanceMatrix, List<Edge> mst) {
            Set<Integer> vertWithOddEdges= findVerticesWithOddDegree(mst,distanceMatrix);
            this.distanceMatrix=distanceMatrix;

            List<Edge> perfMatch=findMinimumWeightPerfectMatching(vertWithOddEdges);
            System.out.println("Minimum Weight Perfect Matching Edges:");

            return perfMatch;
        }

        public Set<Integer> findVerticesWithOddDegree(List<Edge> mst,double[][] distanceMatrix ) {
            Set<Integer> verticesWithOddDegree = new HashSet<>();
            int[] degrees = new int[distanceMatrix.length];

            // Count the degrees of each vertex in the MST
            for (Edge edge : mst) {
                degrees[edge.getSrc()]++;
                degrees[edge.getDest()]++;
            }

            // Check for vertices with odd degree and add them to the set
            for (int i = 0; i < degrees.length; i++) {
                if (degrees[i] % 2 == 1) {
                    verticesWithOddDegree.add(i);
                }
            }

            return verticesWithOddDegree;
        }

        public List<Edge> findMinimumWeightPerfectMatching(Set<Integer> verticesWithOddDegree) {
            int numVertices = distanceMatrix.length;
            List<Edge> perfectMatching = new ArrayList<>();
            boolean[] matched = new boolean[numVertices];
            Arrays.fill(matched, false);
            List<Integer> unmatchedVertices = new ArrayList<>(verticesWithOddDegree);

            while (!unmatchedVertices.isEmpty()) {
                int u = unmatchedVertices.get(0);
                int v = -1;
                double minWeight = Double.MAX_VALUE;

                for (int vertex : unmatchedVertices) {
                    if (u != vertex && distanceMatrix[u][vertex] < minWeight) {
                        minWeight = distanceMatrix[u][vertex];
                        v = vertex;
                    }
                }

                if (v != -1) {
                    perfectMatching.add(new Edge(u, v, distanceMatrix[u][v]));
                    matched[u] = matched[v] = true;
                    unmatchedVertices.remove(Integer.valueOf(u));
                    unmatchedVertices.remove(Integer.valueOf(v));
                }
            }

            return perfectMatching;
        }


    }

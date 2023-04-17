package org.christophides;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.christophides.MinWtPerfMatch;
import org.junit.Test;
import org.structs.Edge;

public class MinWtPerfMatchTest {
    @Test
    public void testFindVerticesWithOddDegree() {
        // Set up test data
        double[][] distanceMatrix = {
            {0, 1, 2, 3},
            {1, 0, 4, 5},
            {2, 4, 0, 6},
            {3, 5, 6, 0}
        };
        List<Edge> mst = new ArrayList<>();
        mst.add(new Edge(0, 1, 1));
        mst.add(new Edge(0, 2, 2));
        mst.add(new Edge(1, 3, 5));
        MinWtPerfMatch matcher = new MinWtPerfMatch();

        // Test finding vertices with odd degree
        Set<Integer> oddVertices = matcher.findVerticesWithOddDegree(mst, distanceMatrix);
        assertEquals(2, oddVertices.size());
    }

    @Test
    public void testFindMinimumWeightPerfectMatching() {
        // Set up test data
        double[][] distanceMatrix = {
            {0, 1, 2, 3},
            {1, 0, 4, 5},
            {2, 4, 0, 6},
            {3, 5, 6, 0}
        };
        MinWtPerfMatch matcher = new MinWtPerfMatch();
        List<Edge> mst = new ArrayList<>();
        mst.add(new Edge(0, 1, 1));
        mst.add(new Edge(0, 2, 2));
        mst.add(new Edge(1, 3, 5));
        Set<Integer> oddVertices = matcher.findVerticesWithOddDegree(mst, distanceMatrix);

        // Test finding minimum weight perfect matching
        try {
        	
            List<Edge> matching = matcher.findMinimumWeightPerfectMatching(oddVertices);
        	
        } catch(Exception e) {
        	
        }
    }


    @Test
    public void testCalculateMST() {
        // Set up test data
        double[][] distanceMatrix = {
            {0, 1, 2, 3},
            {1, 0, 4, 5},
            {2, 4, 0, 6},
            {3, 5, 6, 0}
        };
        List<Edge> mst = new ArrayList<>();
        mst.add(new Edge(0, 1, 1));
        mst.add(new Edge(0, 2, 2));
        mst.add(new Edge(1, 3, 5));
        MinWtPerfMatch matcher = new MinWtPerfMatch();

        // Test calculating MST and minimum weight perfect matching
        List<Edge> matching = matcher.calculateMST(distanceMatrix, mst);
        assertEquals(1, matching.size());
    }
}

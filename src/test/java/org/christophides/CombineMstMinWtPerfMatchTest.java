package org.christophides;

import org.junit.Test;
import org.structs.Edge;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CombineMstMinWtPerfMatchTest {

    @Test
    public void testCombineMSTAndPerfectMatching() {
        // Create some edges for the MST
        List<Edge> mst = new ArrayList<>();
        mst.add(new Edge(1, 2, 1.0));
        mst.add(new Edge(2, 3, 2.0));
        mst.add(new Edge(3, 4, 3.0));

        // Create some edges for the perfect matching
        List<Edge> perfMatch = new ArrayList<>();
        perfMatch.add(new Edge(1, 3, 1.5));
        perfMatch.add(new Edge(2, 4, 2.5));

        // Create the object under test and call the method
        CombineMstMinWtPerfMatch obj = new CombineMstMinWtPerfMatch();
        List<Edge> combined = obj.combineMSTAndPerfectMatching(mst, perfMatch);

        // Check that the combined graph has the expected edges and total distance
        assertEquals(5, combined.size());
        assertEquals(10.0, getTotalDistance(combined), 0.001);
    }

    private double getTotalDistance(List<Edge> edges) {
        double total = 0;
        for (Edge edge : edges) {
            total += edge.getWeight();
        }
        return total;
    }
}

package org.christophides;
import org.christophides.MSTGenerator;
import org.junit.Test;
import org.structs.Edge;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class MSTGeneratorTest {

    @Test
    public void testCalculateMST() {
        double[][] distanceMatrix = {{0, 2, 3}, {2, 0, 1}, {3, 1, 0}};
        MSTGenerator mstGenerator = new MSTGenerator();
        List<Edge> mst = mstGenerator.calculateMST(distanceMatrix);
        assertEquals(2, mst.size()); // the MST for this graph should have two edges
        double expectedDistance = 3.0;
        double actualDistance = mst.stream().mapToDouble(e -> distanceMatrix[e.getSrc()][e.getDest()]).sum();
        assertEquals(expectedDistance, actualDistance, 0.0); // the total distance covered by the MST should be 3.0
    }

    @Test
    public void testGenerateMST() {
        double[][] distanceMatrix = {
                {0, 1, 2},
                {1, 0, 3},
                {2, 3, 0}
        };
        List<Edge> expected = new ArrayList<>();
        expected.add(new Edge(0, 1, 1));
        expected.add(new Edge(1, 2, 3));

        List<Edge> actual = MSTGenerator.generateMST(distanceMatrix);

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getDest(), actual.get(i).getDest());
        }
    }

}

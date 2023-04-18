package org.tacticalopt;
import org.junit.Test;
import org.tacticalopt.TacticalTwoOpt;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TacticalTwoOptTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void testTwoOpt() {
        TacticalTwoOpt twoOpt = new TacticalTwoOpt();

        // Test case 1: empty circuit
        List<Integer> circuit1 = Arrays.asList();
        double[][] matrix1 = {{}};
        List<Integer> expected1 = Arrays.asList();
        List<Integer> result1 = twoOpt.twoOpt(circuit1, matrix1);
        assertEquals(expected1, result1);

        // Test case 2: circuit with one vertex
        List<Integer> circuit2 = Arrays.asList(0);
        double[][] matrix2 = {{0}};
        List<Integer> expected2 = Arrays.asList(0);
        List<Integer> result2 = twoOpt.twoOpt(circuit2, matrix2);
        assertEquals(expected2, result2);

        // Test case 3: circuit with two vertices
        List<Integer> circuit3 = Arrays.asList(0, 1);
        double[][] matrix3 = {{0, 1}, {1, 0}};
        List<Integer> expected3 = Arrays.asList(0, 1);
        List<Integer> result3 = twoOpt.twoOpt(circuit3, matrix3);
        assertEquals(expected3, result3);

        // Test case 4: circuit with three vertices
        List<Integer> circuit4 = Arrays.asList(0, 1, 2);
        double[][] matrix4 = {{0, 1, 2}, {1, 0, 1}, {2, 1, 0}};
        List<Integer> expected4 = Arrays.asList(0, 1, 2);
        List<Integer> result4 = twoOpt.twoOpt(circuit4, matrix4);
        assertEquals(expected4, result4);

        // Test case 5: circuit with four vertices
        List<Integer> circuit5 = Arrays.asList(0, 1, 2, 3);
        double[][] matrix5 = {{0, 1, 2, 3}, {1, 0, 1, 2}, {2, 1, 0, 1}, {3, 2, 1, 0}};
        List<Integer> expected5 = Arrays.asList(0, 1, 2, 3);
        List<Integer> result5 = twoOpt.twoOpt(circuit5, matrix5);
        assertEquals(expected5, result5);

        // Test case 6: circuit with five vertices
        List<Integer> circuit6 = Arrays.asList(0, 1, 2, 3, 4);
        double[][] matrix6 = {{0, 1, 2, 3, 4}, {1, 0, 1, 2, 3}, {2, 1, 0, 1, 2}, {3, 2, 1, 0, 1}, {4, 3, 2, 1, 0}};
        List<Integer> expected6 = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> result6 = twoOpt.twoOpt(circuit6, matrix6);
        assertEquals(expected6, result6);
    }
    
    @Test
    public void testReverseSublist() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 4, 3, 2, 5));
        TacticalTwoOpt.reverseSublist(list, 1, 3);
        assertEquals(expected, list);
    }

    @Test
    public void testReverseSublistEmptyList() {
        List<Integer> list = new ArrayList<>();
        TacticalTwoOpt.reverseSublist(list, 0, 0);
        assertTrue(list.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testReverseSublistInvalidIndices() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        TacticalTwoOpt.reverseSublist(list, 1, 4);
    }

    @Test
    public void testReverseSublistSingleElementList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1));
        TacticalTwoOpt.reverseSublist(list, 0, 0);
        assertEquals(Arrays.asList(1), list);
    }
}

package org.structs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTest {

    @Test
    public void testGetSrc() {
        Edge edge = new Edge(0, 1, 2.0);
        assertEquals(0, edge.getSrc());
    }

    @Test
    public void testGetDest() {
        Edge edge = new Edge(0, 1, 2.0);
        assertEquals(1, edge.getDest());
    }

    @Test
    public void testGetWeight() {
        Edge edge = new Edge(0, 1, 2.0);
        assertEquals(2.0, edge.getWeight(), 0.0001);
    }

    @Test
    public void testSetSrc() {
        Edge edge = new Edge(0, 1, 2.0);
        edge.setSrc(2);
        assertEquals(2, edge.getSrc());
    }

    @Test
    public void testSetDest() {
        Edge edge = new Edge(0, 1, 2.0);
        edge.setDest(3);
        assertEquals(3, edge.getDest());
    }

    @Test
    public void testSetWeight() {
        Edge edge = new Edge(0, 1, 2.0);
        edge.setWeight(4.0);
        assertEquals(4.0, edge.getWeight(), 0.0001);
    }

}

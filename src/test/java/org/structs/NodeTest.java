package org.structs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest {
    @Test
    public void testConstructorAndGetters() {
        Node node = new Node("1", 1.0, 2.0);
        assertEquals("1", node.getId());
        assertEquals(1.0, node.getLongitude(), 0.0);
        assertEquals(2.0, node.getLatitude(), 0.0);
    }
    
    @Test
    public void testSetters() {
        Node node = new Node("1", 1.0, 2.0);
        node.setId("2");
        node.setLongitude(3.0);
        node.setLatitude(4.0);
        assertEquals("2", node.getId());
        assertEquals(3.0, node.getLongitude(), 0.0);
        assertEquals(4.0, node.getLatitude(), 0.0);
    }
    
    @Test
    public void testToString() {
        Node node = new Node("1", 1.0, 2.0);
        assertEquals("Node{id=1, longitude=1.0, latitude=2.0}", node.toString());
    }
}

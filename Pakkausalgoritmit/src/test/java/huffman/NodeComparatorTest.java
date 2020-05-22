/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.SuperNode;
import huffman.Node;
import huffman.NodeComparator;
import huffman.Leaf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Susanna Muhli
 */
public class NodeComparatorTest {
    
    private SuperNode supernode1;
    private SuperNode supernode2;
    private Node node1;
    private Node node2;
    private Leaf leaf1;
    private Leaf leaf2;
    private NodeComparator c;
    
    public NodeComparatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.supernode1 = new SuperNode(1);
        this.supernode2 = new SuperNode(2);
        this.node1 = new Node(1, new SuperNode(1), new SuperNode(1));
        this.node2 = new Node(2, new SuperNode(1), new SuperNode(1));
        this.leaf1 = new Leaf(1, (byte) 1);
        this.leaf2 = new Leaf(2, (byte) 1);
        this.c = new NodeComparator();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compare method, of class NodeComparator.
     */
    @Test
    public void testCompare() {
        assertEquals(c.compare(supernode1, supernode2), -1);
        assertEquals(c.compare(supernode2, supernode1), 1);
        assertEquals(c.compare(node1, node2), -1);
        assertEquals(c.compare(node2, node1), 1);
        assertEquals(c.compare(leaf1, leaf2), -1);
        assertEquals(c.compare(leaf2, leaf1), 1);
        assertEquals(c.compare(supernode1, node1), 0);
        assertEquals(c.compare(supernode1, node2), -1);
        assertEquals(c.compare(node1, supernode2), -1);
        assertEquals(c.compare(node2, supernode2), 0);
        assertEquals(c.compare(node1, leaf1), 1);
        assertEquals(c.compare(leaf1, node1), -1);
        assertEquals(c.compare(leaf1, leaf2), -1);
        assertEquals(c.compare(leaf2, leaf1), 1);
    }
    
}

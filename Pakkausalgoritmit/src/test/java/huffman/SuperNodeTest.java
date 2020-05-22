/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.SuperNode;
import huffman.Node;
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
public class SuperNodeTest {
    
    private SuperNode supernode;
    private Node node;
    private Leaf leaf;
    
    public SuperNodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.supernode = new SuperNode(1);
        this.node = new Node(1, new SuperNode(1), new SuperNode(1));
        this.leaf = new Leaf(1, (byte) 1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class SuperNode.
     */
    @Test
    public void testToString() {
        String expResult1 = "node/weight:1";
        String expResult2 = "node/weight:1 (left child: false) (right child: false)";
        String expResult3 = "leaf/weight:1/byte:1";
        assertEquals(expResult1, supernode.toString());
        assertEquals(expResult2, node.toString());
        assertEquals(expResult3, leaf.toString());
    }

    /**
     * Test of getWeight method, of class SuperNode.
     */
    @Test
    public void testGetWeight() {
        assertEquals(1, supernode.getWeight());
        assertEquals(1, node.getWeight());
        assertEquals(1, leaf.getWeight());
    }
    
}

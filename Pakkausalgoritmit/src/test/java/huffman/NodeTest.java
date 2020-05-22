/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.SuperNode;
import huffman.Node;
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
public class NodeTest {
    
    private Node node;
    
    public NodeTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLeftChild method, of class Node.
     */
    @Test
    public void testGetLeftChild() {
        SuperNode childLeft = new SuperNode(1);
        SuperNode childRight = new SuperNode(1);
        node = new Node(1, childLeft, childRight);
        assertEquals(node.getLeftChild(), childLeft);
    }

    /**
     * Test of getRightChild method, of class Node.
     */
    @Test
    public void testGetRightChild() {
        SuperNode childLeft = new SuperNode(1);
        SuperNode childRight = new SuperNode(1);
        node = new Node(1, childLeft, childRight);
        assertEquals(node.getRightChild(), childRight);
    }
    
}

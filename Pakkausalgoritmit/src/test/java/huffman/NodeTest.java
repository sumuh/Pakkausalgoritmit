/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

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
    private Node leftChild;
    private Node rightChild;
    
    public NodeTest() {
    }
    
    @Before
    public void setUp() {
        leftChild = new Node(1, false);
        rightChild = new Node(1, true);
        node = new Node(2, false);
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLeftChild method, of class Node.
     */
    @Test
    public void testGetLeftChild() {
        assertEquals(node.getLeftChild(), leftChild);
    }
    
    /**
     * Test of setLeftChild method, of class Node.
     */
    @Test
    public void testSetLeftChild() {
        leftChild = new Node(3, true);
        node.setLeftChild(leftChild);
        assertTrue(node.getLeftChild().getIsLeaf());
        assertEquals(node.getLeftChild().getWeight(), 3);
    }

    /**
     * Test of getRightChild method, of class Node.
     */
    @Test
    public void testGetRightChild() {
        assertEquals(node.getRightChild(), rightChild);
    }
    
    /**
     * Test of setRightChild method, of class Node.
     */
    @Test
    public void testSetRightChild() {
        rightChild = new Node(3, false);
        node.setRightChild(rightChild);
        assertFalse(node.getRightChild().getIsLeaf());
        assertEquals(node.getRightChild().getWeight(), 3);
    }
    
    /**
     * Test of getWeight method, of class Node.
     */
    @Test
    public void testGetWeight() {
        assertEquals(node.getWeight(), 2);
    }
    
    /**
     * Test of setByteValue method, of class Node.
     */
    @Test
    public void testSetByteValue() {
        node.setByteValue((byte)4);
        assertEquals(node.getByteValue(), 4);
    }
    
    /**
     * Test of getByteValue method, of class Node.
     */
    @Test
    public void testGetByteValue() {
        node.setByteValue((byte)5);
        assertEquals(node.getByteValue(), 5);
    }
    
    /**
     * Test of getIsLeaf method, of class Node.
     */
    @Test
    public void testGetIsLeaf() {
        assertFalse(node.getIsLeaf());
        assertTrue(rightChild.getIsLeaf());
    }
    
    /**
     * Test of increaseWeight method, of class Node.
     */
    @Test
    public void testIncreaseWeight() {
        node.increaseWeight();
        node.increaseWeight();
        node.increaseWeight();
        assertEquals(node.getWeight(), 5);
    }
    
    /**
     * Test of toString method, of class Node.
     */
    @Test
    public void testToString() {
        rightChild.setByteValue((byte)4);
        String expected1 = "Node weight:2 left child:false right child:true";
        String expected2 = "Leaf weight:1 byte:4";
        assertEquals(expected1, node.toString());
        assertEquals(expected2, rightChild.toString());
    }
    
    /**
     * Test of hashCode method, of class Node.
     */
    @Test
    public void testHashCode() {
        int hash = 5;
        hash = 97 * hash + 2;
        hash = 97 * hash + 0;
        assertEquals(hash, node.hashCode());
    }
    
    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEquals() {
        Node node2 = node;
        assertTrue(node.equals(node2));
        node2 = null;
        assertFalse(node.equals(node2));
        int i = 0;
        assertFalse(node.equals(i));
        node2 = new Node(3, false);
        assertFalse(node.equals(node2));
        node2.setByteValue((byte)1);
        assertFalse(node.equals(node2));
        node2 = new Node(1, true);
        assertFalse(node.equals(node2));
        node2.setLeftChild(rightChild);
        assertFalse(node.equals(node2));
        node2.setRightChild(leftChild);
        assertFalse(node.equals(node2));
    }
    
    /**
     * Test of compareTo method, of class Node.
     */
    @Test
    public void testCompareTo() {
        Node node2 = new Node(2, false);
        Node leafNode1 = new Node(1, true);
        Node leafNode2 = new Node(2, true);
        Node leafNode3 = new Node(2, true);
        leafNode1.setByteValue((byte)1);
        leafNode2.setByteValue((byte)1);
        leafNode3.setByteValue((byte)3);
        assertEquals(node.compareTo(node2), 0);
        assertEquals(node.compareTo(leafNode1), 1);
        assertEquals(leafNode1.compareTo(leafNode2), -1);
        assertEquals(leafNode2.compareTo(leafNode3), -2);
    }
    
}

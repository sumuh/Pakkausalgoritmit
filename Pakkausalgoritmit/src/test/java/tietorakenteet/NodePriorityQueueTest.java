/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

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
public class NodePriorityQueueTest {
    
    NodePriorityQueue pq;
    
    public NodePriorityQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pq = new NodePriorityQueue();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of offer method, of class NodePriorityQueue.
     */
    @Test
    public void testOfferAndPoll() {
        Node node1 = new Node(1, true);
        Node node2 = new Node(2, true);
        Node node3 = new Node(3, true);
        
        pq.offer(node1);
        pq.offer(node2);
        pq.offer(node3);
        
        assertEquals(node1, pq.poll());
        assertEquals(node2, pq.poll());
        assertEquals(node3, pq.poll());
        
        pq.offer(node2);
        pq.offer(node3);
        pq.offer(node1);
        
        assertEquals(node1, pq.poll());
        assertEquals(node2, pq.poll());
        assertEquals(node3, pq.poll());
    }

    /**
     * Test of doubleSize method, of class NodePriorityQueue.
     */
    @Test
    public void testDoubleSize() {
        for (int i = 0; i == 15; i++) {
            pq.offer(new Node(1, true));
        }
    }

    /**
     * Test of getSize method, of class NodePriorityQueue.
     */
    @Test
    public void testGetSize() {
        assertEquals(pq.getSize(), 0);
        
        for (int i = 0; i <= 4; i++) {
            pq.offer(new Node(i, true));
        }
        assertEquals(pq.getSize(), 5);
        
        for (int i = 5; i <= 10; i++) {
            pq.offer(new Node(i, true));
        }
        assertEquals(pq.getSize(), 11);
    }
    
}

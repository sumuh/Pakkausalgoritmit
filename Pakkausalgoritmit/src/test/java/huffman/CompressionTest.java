/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.SuperNode;
import huffman.Compression;
import huffman.Leaf;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
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
public class CompressionTest {
    
    private byte[] input;
    private Compression c;
    
    public CompressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Random random = new Random(5);
        input = new byte[5];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) random.nextInt(127);
        }
        
        c = new Compression(input);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialOrder method, of class Compression.
     */
    @Test
    public void testInitialOrder() {
        ArrayList<Leaf> initialNodes = new ArrayList<>();
        
        for (byte b : input) {
            boolean exists = false;
            for (Leaf i : initialNodes) {
                if (b == i.getByteValue()) {
                    i.weight++;
                    exists = true;
                }
            }
            if (!exists) {
                Leaf newLeaf = new Leaf(1, b);
                initialNodes.add(newLeaf);
            }
        }
        ArrayList<Leaf> testList = c.initialOrder();
        
        for (int i = 0; i < testList.size(); i++) {
            if (!testList.get(i).equals(initialNodes.get(i))) {
                fail("Odotettiin lehteä " + initialNodes.get(i) + ", listalla oli lehti " + testList.get(i));
            }
        }
    }

    /**
     * Test of compress method, of class Compression.
     */
    @Test
    public void testCompress() {
        ArrayList<Leaf> initialNodes = c.initialOrder();
        
        PriorityQueue<SuperNode> pq = new PriorityQueue(new NodeComparator());
        
        for (Leaf l : initialNodes) {
            pq.offer(l);
        }
        
        while (pq.size() > 1) {
            SuperNode node1 = pq.poll();
            SuperNode node2 = pq.poll();
            
            SuperNode newNode = new Node(node1.weight + node2.weight, node1, node2);
            
            pq.offer(newNode);
            System.out.println(pq);
        }
        
        PriorityQueue<SuperNode> testPq = c.compress();
        
        while (pq.size() > 0) {
            SuperNode node1 = pq.poll();
            SuperNode node2 = testPq.poll();
            
            if (!node1.equals(node2)) {
                fail("Odotettiin supernodea " + node1 + ", jonossa oli supernode " + node2);
            }
        }
        
    }
    
}

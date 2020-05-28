/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.*;

/**
 *
 * @author Susanna Muhli
 */
public class CompressionTest {
    
    private byte[] input;
    private Compression c;
    private PriorityQueue<Node> pq;
    private OrderedList<Node> initialNodes;
    private HashMap<Byte, String> table;
    private Node root;
    
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
        input = new byte[]{6, 6, 2, 4, 3};
        c = new Compression(input);
        pq = new PriorityQueue<>();
        initialNodes = c.initialOrder();
        for (int i = 0; i < initialNodes.length(); i++) {
            Node node = initialNodes.get(i);
            pq.offer(node);
        }
        table = new HashMap<>();
        root = initialNodes.get(0);
        
        c.buildTree(pq);
        root = c.getRoot();
        c.buildTable(table, root, 2, "");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compress method, of class Compression.
     */
    @Test
    public void testCompress() {
        File file = c.compress();
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            int treeStringLength = in.read();
            assertTrue(treeStringLength == 39);
            int dataLength = in.read();
            assertTrue(dataLength == 10);
            
            int i;
            int[] expValues = new int[]{32, 80, 52, 18, 12};
            int index = 0;
            while ((i = in.read()) != -1) {
                assertTrue(i == expValues[index]);
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String addPadding(String s) {
        int padding = 8 - s.length();
        String paddingString = "";
        for (int j = 0; j < padding; j++) {
            paddingString += "0";
        }
        s = paddingString + s;
        return s;
    }

    /**
     * Test of initialOrder method, of class Compression.
     */
    @Test
    public void testInitialOrder() {
        OrderedList<Node> result = initialNodes;
        assertTrue((result.get(0).getByteValue() == (byte) 6) && (result.get(0).getWeight() == 2));
        assertTrue((result.get(1).getByteValue() == (byte) 2) && (result.get(1).getWeight() == 1));
        assertTrue((result.get(2).getByteValue() == (byte) 4) && (result.get(2).getWeight() == 1));
        assertTrue((result.get(3).getByteValue() == (byte) 3) && (result.get(3).getWeight() == 1));
    }

    /**
     * Test of buildTree method, of class Compression.
     */
    @Test
    public void testBuildTree() {
        assertTrue("Juuri on node painolla 5", (root.getWeight() == 5) && !root.getIsLeaf());
        
        Node leftChild = root.getLeftChild();
        assertTrue("Juuren vasen lapsi ei ole lehti", !leftChild.getIsLeaf());
        
        Node leaf1 = leftChild.getLeftChild();
        assertTrue("1. lehti on tavu 2", leaf1.getIsLeaf() && leaf1.getByteValue() == (byte)2);
        
        Node leaf2 = leftChild.getRightChild();
        assertTrue("2. lehti on tavu 3", leaf2.getIsLeaf() && leaf2.getByteValue() == (byte)3);
        
        Node rightChild = root.getRightChild();
        assertTrue("Juuren oikea lapsi ei ole lehti", !rightChild.getIsLeaf());
        
        Node leaf3 = rightChild.getLeftChild();
        assertTrue("3. lehti on tavu 4", leaf3.getIsLeaf() && leaf3.getByteValue() == (byte)4);
        
        Node leaf4 = rightChild.getRightChild();
        assertTrue("4. lehti on tavu 6", leaf4.getIsLeaf() && leaf4.getByteValue() == (byte)6);
    }

    /**
     * Test of buildTable method, of class Compression.
     */
    @Test
    public void testBuildTable() {
        assertEquals(table.get((byte)2), "00");
        assertEquals(table.get((byte)3), "01");
        assertEquals(table.get((byte)4), "10");
        assertEquals(table.get((byte)6), "11");
    }

    /**
     * Test of printTable method, of class Compression.
     */
    @Test
    public void testPrintTable() {
        String expected = "Huffman table:\n2: 00\n3: 01\n4: 10\n6: 11\n";
        assertEquals(expected, c.printTable(table));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
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
    private NodePriorityQueue pq;
    private OrderedList<Node> initialNodes;
    private Table<Byte, String> table;
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
        File inputFile = new File("huffmanfiles/toCompress.bin");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(inputFile);
            out.write(input);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        c = new Compression(inputFile);
        
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
            
            byte[] bytes = new byte[10];
            in.read(bytes);
            int originalDataLength = ByteBuffer.wrap(bytes).getInt();
            assertTrue(originalDataLength == 5);
            
            byte[] bytes2 = new byte[10];
            in.read(bytes2);
            int compressedDataLength = ByteBuffer.wrap(bytes2).getInt();
            assertTrue(compressedDataLength == 10);
            
            byte[] bytes3 = new byte[10];
            in.read(bytes3);
            int treeStringLength = ByteBuffer.wrap(bytes3).getInt();
            assertTrue(treeStringLength == 39);
            
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
        
    }

    /**
     * Test of buildTree method, of class Compression.
     */
    @Test
    public void testBuildTree() {
    }

    /**
     * Test of buildTable method, of class Compression.
     */
    @Test
    public void testBuildTable() {
    }
    
}

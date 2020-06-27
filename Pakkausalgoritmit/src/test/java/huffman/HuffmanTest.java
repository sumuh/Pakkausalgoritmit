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
import java.nio.file.Files;
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
public class HuffmanTest {
    
    private byte[] input;
    private Compression c;
    private Decompression d;
    
    public HuffmanTest() {
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
        File inputFile = new File("huffmanfiles/toCompress.txt");
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
            assertTrue("originalDataLength-muuttuja on oikein", originalDataLength == 5);
            
            byte[] bytes2 = new byte[10];
            in.read(bytes2);
            int compressedDataLength = ByteBuffer.wrap(bytes2).getInt();
            assertTrue("compressedDataLength-muuttuja on oikein", compressedDataLength == 10);
            
            byte[] bytes3 = new byte[10];
            in.read(bytes3);
            int treeStringLength = ByteBuffer.wrap(bytes3).getInt();
            System.out.println(treeStringLength);
            assertTrue("treeStringLength-muuttuja on oikein", treeStringLength == 39);
            
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
    
    /**
     * Test of decompress method, of class Decompression.
     */
    @Test
    public void testDecompress() {
        File compressed = c.compress();
        d = new Decompression(compressed, ".txt");
        File decompressed = d.decompress();
        
        try {
            byte[] fileContent = Files.readAllBytes(decompressed.toPath());
            assertEquals("puretun tiedosto sisältö on oikein", fileContent[0], 6);
            assertEquals("puretun tiedosto sisältö on oikein", fileContent[1], 6);
            assertEquals("puretun tiedosto sisältö on oikein", fileContent[2], 2);
            assertEquals("puretun tiedosto sisältö on oikein", fileContent[3], 4);
            assertEquals("puretun tiedosto sisältö on oikein", fileContent[4], 3);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        decompressed.delete();
        
    }
    
    /**
     * Test of addPadding method, of class Compression.
     */
    @Test
    public void testAddPadding() {
        d = new Decompression(c.compress(), ".txt");
        String expResult1 = "00001111";
        String expResult2 = "00000000";
        String s1 = d.addPadding("1111");
        String s2 = d.addPadding("0000");
        assertEquals("addPadding toimii", expResult1, s1);
        assertEquals("addPadding toimii", expResult2, s2);
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
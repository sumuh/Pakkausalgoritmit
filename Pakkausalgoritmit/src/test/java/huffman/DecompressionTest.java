/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
public class DecompressionTest {
    
    private Compression c;
    private Decompression d;
    private File file;
    private FileInputStream in;
    
    public DecompressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        byte[] input = new byte[]{6, 6, 2, 4, 3};
        this.c = new Compression(input);
        this.d = new Decompression();
        file = c.compress();
        try {
            in = new FileInputStream(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decompress method, of class Decompression.
     */
    @Test
    public void testDecompress() {
        
    }

    /**
     * Test of readFile method, of class Decompression.
     */
    @Test
    public void testReadFile() {
        d.readFile(file);
    }

    /**
     * Test of formatData method, of class Decompression.
     */
    @Test
    public void testFormatData() {
        try {
            in.read();
            in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        String expResult1 = "001000000101000000110100000100100000110";
        String expResult2 = "1111001001";
        
        String s1 = d.formatData(in, 39);
        String s2 = d.formatData(in, 10);
        
        assertEquals(expResult1, s1);
        assertEquals(expResult2, s2);
    }

    /**
     * Test of addPadding method, of class Decompression.
     */
    @Test
    public void testAddPadding() {
        String expResult1 = "00001111";
        String expResult2 = "00000000";
        String s1 = d.addPadding("1111");
        String s2 = d.addPadding("0000");
        assertEquals(expResult1, s1);
        assertEquals(expResult2, s2);
    }

    /**
     * Test of buildTree method, of class Decompression.
     */
    @Test
    public void testBuildTree() {
    }
    
}

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
        file = c.compress();
        this.d = new Decompression(file, ".bin");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decompress method, of class Decompression.
     */
    @Test
    public void testDecompress() {
        File decompressed = d.decompress();
        
        try {
            byte[] fileContent = Files.readAllBytes(decompressed.toPath());
            assertEquals(fileContent[0], 6);
            assertEquals(fileContent[1], 6);
            assertEquals(fileContent[2], 2);
            assertEquals(fileContent[3], 4);
            assertEquals(fileContent[4], 3);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test of readFile method, of class Decompression.
     */
    @Test
    public void testReadFile() {
    }

    /**
     * Test of formatData method, of class Decompression.
     */
    @Test
    public void testFormatData() {
        
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

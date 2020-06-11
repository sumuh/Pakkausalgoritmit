/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
public class LzwTest {
    
    private lzw.Compression c;
    private lzw.Decompression d;
    
    public LzwTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compress method, of class Compression.
     */
    @Test
    public void testCompressAndDecompress() {
        
        File inputFile = new File("lzwfiles/text.txt");
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque dolor nibh, faucibus a ex quis, tempor lobortis felis.";
        
        try (FileWriter fw = new FileWriter(inputFile)) {
            fw.write(testString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        c = new Compression(inputFile);
        File compressed = c.compress();
        
        d = new lzw.Decompression(compressed);
        File decompressed = d.decompress();
        
        try (BufferedReader br = new BufferedReader(new FileReader(decompressed));) {
            String result = br.readLine();
            assertEquals("Puretussa tiedostossa oikea sisältö", result, testString);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of writeToFile method, of class Compression.
     */
    @Test
    public void testWriteToFile() {
    }

    /**
     * Test of intTo12Bit method, of class Compression.
     */
    @Test
    public void testIntTo12Bit() {
    }
    
}

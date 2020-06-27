/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

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
public class ByteValueTest {
    
    public ByteValueTest() {
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
     * Test of getByteValue method, of class ByteValue.
     */
    @Test
    public void testGetByteValue() {
        ByteValue bv1 = new ByteValue((byte) 1);
        ByteValue bv2 = new ByteValue((byte) 2);
        byte b1 = bv1.getByteValue();
        byte b2 = bv2.getByteValue();
        assertEquals(b1, (byte) 1);
        assertEquals(b2, (byte) 2);
    }
    
}

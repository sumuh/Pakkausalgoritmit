/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.Leaf;
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
public class LeafTest {
    
    private Leaf leaf;
    
    public LeafTest() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getByteValue method, of class Leaf.
     */
    @Test
    public void testGetByteValue() {
        leaf = new Leaf(1, (byte) 3);
        assertEquals(leaf.getByteValue(), (byte) 3);
        Leaf leaf2 = new Leaf(1, (byte) 0b00010001);
        assertEquals(leaf2.getByteValue(), (byte) 17);
    }

    /**
     * Test of toString method, of class Leaf.
     */
    @Test
    public void testToString() {
        this.leaf = new Leaf(1, (byte) 1);
        String expResult = "leaf/weight:1/byte:1";
        String result = leaf.toString();
        assertEquals(expResult, result);
    }
    
}

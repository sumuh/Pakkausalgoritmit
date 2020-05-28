/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Objects;
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
public class EntryTest {
    
    Entry<Integer, String> entry1;
    Entry<Integer, String> entry2;
    
    public EntryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        entry1 = new Entry<>(1, "aaa");
        entry2 = new Entry<>(2, "bbb");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hashCode method, of class Entry.
     */
    @Test
    public void testHashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(1);
        assertTrue(hash == entry1.hashCode());
    }

    /**
     * Test of equals method, of class Entry.
     */
    @Test
    public void testEquals() {
        assertTrue(entry1.equals(entry1));
        entry2 = null;
        assertFalse(entry1.equals(entry2));
        assertFalse(entry1.equals("ccc"));
        entry2 = new Entry<>(2, "aaa");
        assertFalse(entry1.equals(entry2));
        entry2 = new Entry<>(1, "bbb");
        assertFalse(entry1.equals(entry2));
        entry2 = new Entry<>(1, "aaa");
        assertTrue(entry1.equals(entry2));
    }

    /**
     * Test of toString method, of class Entry.
     */
    @Test
    public void testToString() {
        String expResult = "Key: 1, value: aaa";
        assertEquals(expResult, entry1.toString());
    }
    
}

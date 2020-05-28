/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

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
public class TableTest {
    
    Table<Integer, String> table1;
    
    public TableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        table1 = new Table(10);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Table.
     */
    @Test
    public void testAdd() {
        table1.add(1, "one");
        table1.add(2, "two");
        table1.add(3, "one");
        assertTrue(table1.getSize() == 3);
        
        table1.add(1, "oneTwo");
        assertTrue(table1.getSize() == 3);
        
        table1.add(11, "eleven");
        assertTrue(table1.getSize() == 4);
        table1.add(21, "twenty-one");
        assertTrue(table1.getSize() == 5);
    }
    
    /**
     * Test of get method, of class Table.
     */
    @Test
    public void testGet() {
        table1.add(1, "one");
        table1.add(2, "two");
        assertTrue(table1.get(1).equals("one"));
    }
    
    /**
     * Test of get method, of class Table.
     */
    @Test(expected = NullPointerException.class)
    public void testGetWithBadKey() {
        table1.add(1, "one");
        table1.add(2, "two");
        table1.get(3);
    }
    
    /**
     * Test of toString method, of class Table.
     */
    @Test
    public void testToString() {
        table1.add(1, "one");
        table1.add(2, "two");
        
        String expResult = "Key: 1, value: one\nKey: 2, value: two\n";
        assertEquals(expResult, table1.toString());
    }
    
}

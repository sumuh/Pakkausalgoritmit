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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Susanna Muhli
 */
public class OrderedListTest {
    
    private OrderedList<Integer> intList;
    private OrderedList<String> stringList;
    
    public OrderedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        intList = new OrderedList<>();
        stringList = new OrderedList<>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of methods add and get, of class OrderedList.
     */
    @Test
    public void testAddAndGet() {
        intList.add(2);
        intList.add(2);
        intList.add(1);
        intList.add(4);
        
        assertTrue(intList.get(0) == 2);
        assertTrue(intList.get(1) == 2);
        assertTrue(intList.get(2) == 1);
        assertTrue(intList.get(3) == 4);
        
        stringList.add("aaa");
        stringList.add("ccc");
        stringList.add("bbb");
        stringList.add("aaa");
        
        assertTrue(stringList.get(0).equals("aaa"));
        assertTrue(stringList.get(1).equals("ccc"));
        assertTrue(stringList.get(2).equals("bbb"));
        assertTrue(stringList.get(3).equals("aaa"));
    }
    
    /**
     * Test of remove method, of class OrderedList.
     */
    @Test
    public void testRemove() {
        intList.add(2);
        intList.add(2);
        intList.add(1);
        intList.add(4);
        
        intList.remove(1);
        assertTrue(intList.get(0) == 2);
        assertTrue(intList.get(1) == 1);
        assertTrue(intList.get(2) == 4);
    }

    /**
     * Test of doubleSize method, of class OrderedList.
     */
    @Test
    public void testDoubleSize() {
        for (int i = 0; i <= 15; i++) {
            intList.add(i);
        }
        assertTrue(intList.get(10) == 10);
        assertTrue(intList.get(15) == 15);
    }

    /**
     * Test of toString method, of class OrderedList.
     */
    @Test
    public void testToString() {
        intList.add(2);
        intList.add(2);
        intList.add(1);
        intList.add(4);
        
        String expResult = "[2, 2, 1, 4]";
        assertTrue(expResult.equals(intList.toString()));
    }
    
    /**
     * Test of checkBounds method, of class OrderedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void bigIndexThrowsException() {
        intList.add(1);
        intList.checkBounds(1);
    }
    
    /**
     * Test of checkBounds method, of class OrderedList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void negativeIndexThrowsException() {
        intList.add(2);
        intList.checkBounds(-1);
    }
    
}

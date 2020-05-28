/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

/**
 *
 * @author Susanna Muhli
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Table<Integer, String> t = new Table<>(5);
        t.add(1, "one");
        t.add(1, "oneTwo");
        t.add(3, "three");
        t.add(13, "threeHash");
        System.out.println(t.get(13));
        
    }
    
}

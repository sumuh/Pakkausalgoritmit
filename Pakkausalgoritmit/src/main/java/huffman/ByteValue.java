/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 * Luokka on olemassa koska byte ei voi olla null, mutta nodeilla joko on tai ei ole byteä.
 * Kun byte on oma luokkansa niin se voi olla tarvittaessa null.
 * 
 * @author Susanna Muhli
 */
public class ByteValue {
    
    byte value;
    
    /**
     * 
     * @param value 
     */
    public ByteValue(byte value) {
        this.value = value;
    }
    
    /**
     * 
     * @return 
     */
    public byte getByteValue() {
        return this.value;
    }
    
}

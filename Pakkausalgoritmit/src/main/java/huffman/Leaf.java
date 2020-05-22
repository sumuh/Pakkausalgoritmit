/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author Susanna Muhli
 */
public class Leaf extends SuperNode {
    
    private byte byteValue;
    
    /**
     *
     * @param weight
     * @param byteValue
     */
    public Leaf(int weight, byte byteValue) {
        super(weight);
        this.byteValue = byteValue;
        this.isLeaf = true;
    }
    
    /**
     *
     * @return lehden sisältämän byten arvo
     */
    public byte getByteValue() {
        return this.byteValue;
    }
    
    @Override
    public String toString() {
        return super.toString() + "/byte:" + this.byteValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.byteValue;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Leaf other = (Leaf) obj;
        if (this.byteValue != other.byteValue) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        return true;
    }
    
    
    
}

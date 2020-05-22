/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 * Yläluokka nodeille ja lehdille jotta niillä voi olla yhteinen comparator priorityqueueta varten
 * Lehdet ja nodet eroavat toisistaan vain siinä että lehdillä on byte-arvo ja nodeilla ei, ja nodeilla on child nodet joita lehdillä ei ole
 * 
 * @author Susanna Muhli
 * 
 */

public class SuperNode {
    
    int weight;
    boolean isLeaf;
    
    public SuperNode(int weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        if (isLeaf) {
            return "leaf/weight:" + this.weight;
        } else {
            return "node/weight:" + this.weight;
        }
    }
    
    /**
     *
     * @return paino
     */
    public int getWeight() {
        return this.weight;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.weight;
        hash = 13 * hash + (this.isLeaf ? 1 : 0);
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
        final SuperNode other = (SuperNode) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (this.isLeaf != other.isLeaf) {
            return false;
        }
        return true;
    }
    
    
    
}

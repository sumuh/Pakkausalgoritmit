/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Objects;

/**
 *
 * @author Susanna Muhli
 */
public class Node implements Comparable<Node> {
    
    private Node leftChild;
    private Node rightChild;
    private int weight;
    private ByteValue byteValue;
    private boolean isLeaf;
    
    /**
     *
     * @param weight
     * @param isLeaf
     */
    public Node(int weight, boolean isLeaf) {
        this.weight = weight;
        this.isLeaf = isLeaf;
    }

    /**
     *
     * @return vasemmalla alapuolella oleva node/lehti
     */
    public Node getLeftChild() {
        return leftChild;
    }
    
    /**
     * 
     * @param node 
     */
    public void setLeftChild(Node node) {
        this.leftChild = node;
    }

    /**
     *
     * @return oikealla alapuolella oleva node/lehti
     */
    public Node getRightChild() {
        return rightChild;
    }
    
    /**
     * 
     * @param node 
     */
    public void setRightChild(Node node) {
        this.rightChild = node;
    }

    /**
     * 
     * @return paino
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 
     * @return noden sisältämä tavu jos node on lehti, muuten null
     */
    public byte getByteValue() {
        return this.byteValue.getByteValue();
    }
    
    /**
     * 
     * @param byteValue 
     */
    public void setByteValue(byte byteValue) {
        this.byteValue = new ByteValue(byteValue);
    }

    /**
     * 
     * @return onko node lehti
     */
    public boolean getIsLeaf() {
        return isLeaf;
    }
    
    /**
     * lisää noden painoa yhdellä
     */
    public void increaseWeight() {
        this.weight++;
    }
    
    /**
     * 
     * @return noden tiedot
     */
    @Override
    public String toString() {
        if (this.isLeaf) {
            return "Leaf weight:" + this.weight + " byte:" + this.getByteValue();
        }
        return "Node weight:" + this.weight + " left child:" + this.leftChild.isLeaf + " right child:" + this.rightChild.isLeaf;
    }

    /**
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.weight;
        hash = 97 * hash + (this.isLeaf ? 1 : 0);
        return hash;
    }

    /**
     * 
     * @param obj
     * @return equals
     */
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
        final Node other = (Node) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (this.getByteValue() != other.getByteValue()) {
            return false;
        }
        if (this.isLeaf != other.isLeaf) {
            return false;
        }
        if (!Objects.equals(this.leftChild, other.leftChild)) {
            return false;
        }
        if (!Objects.equals(this.rightChild, other.rightChild)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param o
     * @return vertailun tulos
     */
    @Override
    public int compareTo(Node o) {
        if (this.weight == o.weight) {
            if (this.isLeaf && !o.isLeaf) {
                return -1;
            }
            if (!this.isLeaf && o.isLeaf) {
                return 1;
            }
//            if (this.isLeaf && o.isLeaf) {
//                int i = this.getByteValue() - o.getByteValue();
//                return i;
//            }
        }
        return this.weight - o.weight;
    }

    
    
    
    
}

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
public class Node extends SuperNode {
    
    private SuperNode leftChild;
    private SuperNode rightChild;
    
    /**
     *
     * @param weight
     * @param leftChild
     * @param rightChild
     */
    public Node(int weight, SuperNode leftChild, SuperNode rightChild) {
        super(weight);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.isLeaf = false;
    }

    /**
     *
     * @return vasemmalla alapuolella oleva node/lehti
     */
    public SuperNode getLeftChild() {
        return leftChild;
    }

    /**
     *
     * @return oikealla alapuolella oleva node/lehti
     */
    public SuperNode getRightChild() {
        return rightChild;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (left child: " + this.leftChild.isLeaf + ") (right child: " + this.rightChild.isLeaf + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.leftChild);
        hash = 13 * hash + Objects.hashCode(this.rightChild);
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
        final Node other = (Node) obj;
        if (!Objects.equals(this.leftChild, other.leftChild)) {
            return false;
        }
        if (!Objects.equals(this.rightChild, other.rightChild)) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        return true;
    }
    
    
    
}

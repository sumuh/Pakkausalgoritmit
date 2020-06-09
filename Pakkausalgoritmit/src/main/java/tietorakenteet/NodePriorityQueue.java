/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import huffman.Node;

/**
 * PriorityQueuea vastaava tietorakenne. Hyväksyy pelkkiä nodeja. Toteutettu tehokkuussyistä min heapin avulla.
 * 
 * @author Susanna Muhli
 */
public class NodePriorityQueue {
    
    private int capacity;
    private int size;
    private Node[] nodes;
    
    public NodePriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.nodes = new Node[capacity + 1];
    }
    
    /**
     * 
     * @param node lisättävä node
     */
    public void offer(Node node) {
        size++;
        nodes[size] = node;
        int current = size;
        
        while (current > 1 && nodes[current].compareTo(nodes[current / 2]) < 0) {
            swap(current, current / 2);
            current = current / 2;
        }
    }
    
    /**
     * 
     * @return jonon ensimmäinen eli prioriteetiltaan korkein node
     */
    public Node poll() {
        Node ret = nodes[1];
        nodes[1] = nodes[size];
        nodes[size] = null;
        size--;
        heapifyDown();
        return ret;
    }
    
    /**
     * kun poistetaan juurinode, siirretään tällä metodilla nodet paikoilleen
     */
    public void heapifyDown() {
        int current = 1;
        
        while (hasLeftChild(current)) {
            int leftChild = 2 * current;
            int smaller = leftChild;
            if (hasRightChild(current)) {
                int rightChild = 2 * current + 1;
                if (nodes[leftChild].compareTo(nodes[rightChild]) > 0) {
                    smaller = rightChild;
                }
            }
            if (nodes[current].compareTo(nodes[smaller]) > 0) {
                swap(current, smaller);
                current = smaller;
            } else {
                break;
            }
        }
        
    }
    
    /**
     * vaihtaa parameteinä annettujen nodejen paikkoja
     * @param i
     * @param j 
     */
    public void swap(int i, int j) {
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }
    
    /**
     * 
     * @return onko jono tyhjä
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    /**
     * 
     * @return jonon koko
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * 
     * @param i
     * @return onko parametrina annetun indeksin nodella vasenta lasta
     */
    public boolean hasLeftChild(int i) {
        return i * 2 <= size;
    }
    
    /**
     * 
     * @param i
     * @return  onko parametrina annetun indeksin nodella oikeaa lasta
     */
    public boolean hasRightChild(int i) {
        return i * 2 + 1 <= size;
    }
    
    /**
     * 
     * @param i
     * @return onko parametrina annetun indeksin nodella ylempää nodea
     */
    public boolean hasParent(int i) {
        return i > 1;
    }
    
}

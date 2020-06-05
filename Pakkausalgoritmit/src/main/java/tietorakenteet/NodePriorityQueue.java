/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import huffman.Node;

/**
 * PriorityQueuea vastaava tietorakenne. Hyväksyy pelkkiä nodeja.
 * 
 * @author Susanna Muhli
 */
public class NodePriorityQueue {
    
    private Node[] nodes;
    private int size;
    
    public NodePriorityQueue() {
        this.nodes = new Node[10];
        this.size = 0;
    }
    
    /**
     * Vertaa uutta nodea olemassa oleviin nodeihin ja sijoittaa jonoon prioriteetin mukaan
     * @param node 
     */
    public void offer(Node node) {
        if (size == nodes.length) {
            doubleSize();
        }
        
        if (size == 0) {
            nodes[0] = node;
        } else {
            boolean inserted = false;
            for (int i = 0; i < size; i++) {
                if (node.compareTo(nodes[i]) < 0) {
                    inserted = true;
                    Node t = nodes[i];
                    nodes[i] = node;
                    while (i < size - 1) {
                        Node next = nodes[i+1];
                        nodes[i+1] = t;
                        t = next;
                        i++;
                    }
                    nodes[size] = t;
                    break;
                }
            }
            if (!inserted) {
                nodes[size] = node;
            }
        }
        size++;
    }
    
    /**
     * 
     * @return jonon ensimmäinen node 
     */
    public Node poll() {
        Node toReturn = nodes[0];
        Node t = nodes[0];
        for (int i = 1; i < size; i++) {
            nodes[i-1] = nodes[i];
        }
        size--;
        return toReturn;
    }
    
    /**
     * tuplaa listan koon
     */
    public void doubleSize() {
        Node[] newList = new Node[nodes.length * 2];
        for (int i = 0; i < nodes.length; i++) {
            newList[i] = nodes[i];
        }
        nodes = newList;
    }
    
    public int getSize() {
        return this.size;
    }
    
    /**
     * 
     * @return jonon nodejen painot
     */
    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s += nodes[i].getWeight() + " ";
        }
        s += "]";
        return s;
    }
    
}

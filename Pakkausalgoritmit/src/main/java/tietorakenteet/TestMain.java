/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import huffman.Node;

/**
 *
 * @author Susanna Muhli
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        NodePriorityQueue pq = new NodePriorityQueue();
        Node node1 = new Node(1, true);
        Node node2 = new Node(2, true);
        Node node3 = new Node(3, true);
        Node node4 = new Node(4, true);
        pq.offer(node2);
        pq.offer(node4);
        pq.offer(node3);
        pq.offer(node1);
        System.out.println(pq);
        pq.poll();
        pq.poll();
        pq.poll();
        System.out.println(pq);
    }
    
}

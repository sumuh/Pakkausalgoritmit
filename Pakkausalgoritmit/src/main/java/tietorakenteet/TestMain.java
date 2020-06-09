/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import huffman.Node;
import java.util.Random;

/**
 *
 * @author Susanna Muhli
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        NodePriorityQueue pq = new NodePriorityQueue(10);
        pq.offer(new Node(30, true));
        pq.offer(new Node(12, true));
        pq.offer(new Node(3, true));
        pq.offer(new Node(5, true));
        pq.offer(new Node(22, true));
        while (!pq.isEmpty()) {
            System.out.println(pq.poll().getWeight());
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.List;

/**
 *
 * @author Susanna Muhli
 */
public class Compression {
   
    private byte[] input;
    private PriorityQueue<SuperNode> pq;
    
    /**
     *
     * @param input
     */
    public Compression(byte[] input) {
        this.input = input;
        this.pq = new PriorityQueue(new NodeComparator());
    }

    /**
     *
     * @return lista jossa lehdet painoineen
     */
    public ArrayList<Leaf> initialOrder() {
        ArrayList<Leaf> initialNodes = new ArrayList<>();
        
        for (byte b : input) {
            boolean exists = false;
            for (Leaf i : initialNodes) {
                if (b == i.getByteValue()) {
                    i.weight++;
                    exists = true;
                }
            }
            if (!exists) {
                Leaf newLeaf = new Leaf(1, b);
                initialNodes.add(newLeaf);
            }
        }
        return initialNodes;
    }
    
    /**
     *
     * @return priorityqueue
     */
    public PriorityQueue<SuperNode> compress() {
        ArrayList<Leaf> initialNodes = this.initialOrder();
        
        for (Leaf l : initialNodes) {
            pq.offer(l);
        }
        
        System.out.println(pq);
        
        while (pq.size() > 1) {
            SuperNode node1 = pq.poll();
            SuperNode node2 = pq.poll();
            
            SuperNode newNode = new Node(node1.weight + node2.weight, node1, node2);
            
            pq.offer(newNode);
            System.out.println(pq);
        }
        
        return pq;
    }
    
}

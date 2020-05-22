/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Comparator;

/**
 * Comparatorin avulla verrataan supernodeja niin että priorityqueuessa lehdillä on aina korkeampi priority kuin nodeilla ja muuten verrataan painoja
 * @author Susanna Muhli
 */
public class NodeComparator implements Comparator<SuperNode> {

    @Override
    public int compare(SuperNode node1, SuperNode node2) {
        
        if (node1.isLeaf && !node2.isLeaf) {
            return -1;
        } else if (!node1.isLeaf && node2.isLeaf) {
            return 1;
        } else {
            return node1.weight - node2.weight;
        }
        
    }
    
}

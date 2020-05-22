/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Susanna Muhli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        byte[] input = new byte[4];
//        input[0] = (byte) 0b00000001;
//        input[1] = (byte) 0b00000011;
//        input[2] = (byte) 0b00000111;
//        input[3] = (byte) 0b00000001;

        Random random = new Random();
        byte[] input = new byte[7];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) random.nextInt(7);
            System.out.println(input[i]);
        }
        //byte[] input = new byte[]{(byte) 2, (byte) 3, (byte) 4, (byte) 4, (byte) 1};
        Compression c = new Compression(input);
        PriorityQueue<SuperNode> pq = c.compress();
    }
    
}

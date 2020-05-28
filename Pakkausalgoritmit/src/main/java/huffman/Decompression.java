/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 *
 * @author Susanna Muhli
 */
public class Decompression {
    
    private String treeString;
    private String compressedData;
    
    public Decompression() {
    }
    
    /**
     * 
     * @param file 
     */
    public void decompress(File file) {
        readFile(file);
    }
    
    /**
     * 
     * @param file 
     */
    public void readFile(File file) {
        FileInputStream in = null;
        
        try {
            in = new FileInputStream(file);
            System.out.println("");
            System.out.println("--Decompression--");
            
            //read header info
            int treeStringLength = in.read();
            int dataLength = in.read();
            
            
            treeString = formatData(in, treeStringLength);
            System.out.println("Tree string: " + treeString);
            
            compressedData = formatData(in, dataLength);
            System.out.println("Compressed data: " + compressedData);
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * 
     * @param in
     * @param length
     * @return data alkuperäisessä tiivistetyssä muodossa
     */
    public String formatData(FileInputStream in, int length) {
        String s = "";
        int i;
        try {
            while (true) {
                i = in.read();
                String bits = Integer.toBinaryString(i);
                
                if (bits.length() != 8) {
                    bits = addPadding(bits);
                }
                
                if (s.length() + bits.length() > length) {
                    int padding = s.length() + bits.length() - length;
                    bits = bits.substring(0, bits.length() - padding);
                    s += bits;
                    break;
                }
                s += bits;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return s;
    }
    
    /**
     * 
     * @param s
     * @return syötteenä annettu string "8-bittisenä" (metodi lisää tarvittavat nollat)
     */
    public String addPadding(String s) {
        int padding = 8 - s.length();
        String paddingString = "";
        for (int j = 0; j < padding; j++) {
            paddingString += "0";
        }
        s = paddingString + s;
        return s;
    }
    
    /**
     * kesken
     */
    public void buildTree() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        char[] chars = compressedData.toCharArray();
        
        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            if (c == 1) {
                String s = "";
                int j;
                for (j = i; j < i + 8; j++) {
                    s += chars[j];
                }
                byte b = Byte.parseByte(s, 2);
                Node node = new Node(1, true);
                node.setByteValue(b);
                pq.offer(node);
                i = j;
            } else {
                Node node1 = pq.poll();
                if (!pq.isEmpty()) {
                    Node node2 = pq.poll();
                    Node newNode = new Node(1, false);
                    newNode.setRightChild(node1);
                    newNode.setLeftChild(node2);
                    pq.offer(newNode);
                }
            }
        }
    }
    
}

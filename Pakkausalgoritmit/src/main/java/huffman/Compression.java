/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.PriorityQueue;
import tietorakenteet.*;

/**
 *
 * @author Susanna Muhli
 */
public class Compression {
   
    private byte[] input;
    private Node root;
    private String treeString;
    private File inputFile;
    
    /**
     *
     * @param inputFile
     */
    public Compression(File inputFile) {
        this.inputFile = inputFile;
        this.treeString = "";
    }
    
    /**
     * 
     * @return tiivistetty data .bin-tiedostona
     */
    public File compress() {
        
        try {
            input = Files.readAllBytes(inputFile.toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        OrderedList<Node> initialNodes = this.initialOrder();
        root = initialNodes.get(0);
        
        NodePriorityQueue pq = new NodePriorityQueue(initialNodes.length());
        Table<Byte, String> table = new Table(5);

        for (Node node : initialNodes) {
            pq.offer(node);
        }
        
        buildTree(pq);
        
        buildTable(table, root, 2, "");
        
        String compressedData = compressToString(table);
        
        this.treeToString(root);
        
        File file = new File("huffmanfiles/compressed.bin");
        writeToFile(file, compressedData);
        
        return file;
    }
    
    /**
     * 
     * @param table
     * @return input-data tiivistettynä stringiin huffman-tablen mukaan
     */
    public String compressToString(Table<Byte, String> table) {
        
        String compressedData = "";
        for (byte b : input) {
            compressedData += table.get(b);
        }
        return compressedData;
    }
    
    /**
     * 
     * @param file
     * @param compressedData 
     */
    public void writeToFile(File file, String compressedData) {
        FileOutputStream out = null;
        
        try {
            out = new FileOutputStream(file);
            
            // kirjoita otsakkeet tiedostoon
            byte[] bytes = ByteBuffer.allocate(10).putInt(input.length).array();
            out.write(bytes);
            byte[] bytes2 = ByteBuffer.allocate(10).putInt(compressedData.length()).array();
            out.write(bytes2);
            byte[] bytes3 = ByteBuffer.allocate(10).putInt(treeString.length()).array();
            out.write(bytes3);
            
            
            // kirjoita data tiedostoon
            formatAndWriteDataToFile(out, treeString);
            formatAndWriteDataToFile(out, compressedData);
            
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void formatAndWriteDataToFile(FileOutputStream out, String data) {
        try {
            String s = "";
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                s += c;
                if (i == data.length() - 1) {
                    while (s.length() < 8) {
                        s += "0";
                    }
                    out.write((byte) Integer.parseInt(s, 2));
                } else if (s.length() == 8) {
                    out.write((byte) Integer.parseInt(s, 2));
                    s = "";
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     *
     * @return lista jossa lehdet painoineen
     */
    public OrderedList<Node> initialOrder() {
        OrderedList<Node> initialNodes = new OrderedList<>();
        
        for (byte b : input) {
            boolean exists = false;
            for (Node node : initialNodes) {
                if (b == node.getByteValue()) {
                    node.increaseWeight();
                    exists = true;
                }
            }
            if (!exists) {
                Node newNode = new Node(1, true);
                newNode.setByteValue(b);
                initialNodes.add(newNode);
            }
        }
        return initialNodes;
    }
    
    /**
     * 
     * @param pq
     */
    public void buildTree(NodePriorityQueue pq) {
        while (pq.getSize() > 1) {
            Node node1 = pq.poll();
            Node node2 = pq.poll();
            
            Node newNode = new Node(node1.getWeight() + node2.getWeight(), false);
            newNode.setLeftChild(node1);
            newNode.setRightChild(node2);
            
            root = newNode;
            
            pq.offer(newNode);
        }
    }
    
    /**
     * Muodostaa puun rakenteen perusteella stringin jonka avulla decompression-luokka tietää puun rakenteen
     *
     * @param node 
     */
    public void treeToString(Node node) {
        if (node.getIsLeaf()) {
            treeString += "1";
            //String s1 = String.format("%8s", Integer.toBinaryString(node.getByteValue() & 0xFF)).replace(' ', '0');
            String s1 = Integer.toBinaryString((node.getByteValue() & 0xFF) + 0x100).substring(1);
            
            treeString += s1;
            return;
        } else {
            treeString += "0";
            treeToString(node.getLeftChild());
            treeToString(node.getRightChild());
        }
    }
    
    /**
     * 
     * @param table
     * @param node
     * @param lastEdge
     * @param current 
     */
    public void buildTable(Table<Byte, String> table, Node node, int lastEdge, String current) {
        if (node == null) {
            return;
        }
        if (lastEdge != 2) {
            current += lastEdge;
        }
        if (node.getIsLeaf()) {
            table.add(node.getByteValue(), current);
            current = "";
        } else {
            buildTable(table, node.getLeftChild(), 0, current);
            buildTable(table, node.getRightChild(), 1, current);
        }
        
    }
    
    /**
     * 
     * @param table 
     * @return s
     */
    public String printTable(Table<Byte, String> table) {
        String s = "";
        s += "Huffman table:\n";
        for (Entry e : table) {
            s += e.toString();
        }
        return s;
    }
    
    /**
     * 
     * @return juurinode
     */
    public Node getRoot() {
        return this.root;
    }
    
}

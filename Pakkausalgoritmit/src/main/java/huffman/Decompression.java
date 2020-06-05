/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import tietorakenteet.Entry;
import tietorakenteet.Table;

/**
 *
 * @author Susanna Muhli
 */
public class Decompression {
    
    private String treeString;
    private String compressedData;
    private char[] chars;
    private int index;
    private String filetype;
    private int originalDataLength;
    private File file;
    
    public Decompression(File file, String filetype) {
        this.file = file;
        this.filetype = filetype;
    }
    
    /**
     * 
     * @return dekompressoitu tiedosto
     */
    public File decompress() {
        
        System.out.println("-Decompression-");
        
        System.out.println("readFile:");
        
        readFile(file);
        
        System.out.println("..done");
        
        chars = treeString.toCharArray();
        index = 0;
        
        Node root = new Node(1, false);
        
        System.out.println("buildTree:");
        
        root = buildTree();
        
        System.out.println("..done");
        
        Table<Byte, String> table = new Table(20);
        
        System.out.println("buildTable:");
        
        buildTable(table, root, 2, "");
        
        System.out.println("..done");
        
        System.out.println("getOutput:");
        
        byte[] output = getOutput(table, root);
        
        System.out.println("..done");
        
        File decompressedFile = new File("huffmanfiles/decompressed" + filetype);
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(decompressedFile);
            out.write(output);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return decompressedFile;
    }
    
    /**
     * 
     * @param file 
     */
    public void readFile(File file) {
        FileInputStream in = null;
        
        try {
            in = new FileInputStream(file);
            
            //read header info
            byte[] bytes = new byte[10];
            in.read(bytes);
            originalDataLength = ByteBuffer.wrap(bytes).getInt();
            
            byte[] bytes2 = new byte[10];
            in.read(bytes2);
            int dataLength = ByteBuffer.wrap(bytes2).getInt();
            
            byte[] bytes3 = new byte[10];
            in.read(bytes3);
            int treeStringLength = ByteBuffer.wrap(bytes3).getInt();
            
            System.out.println("formatData (treeString)");
            treeString = formatData(in, treeStringLength);
            System.out.println("..done");
            System.out.println("formatData (compressedData)");
            compressedData = formatData(in, dataLength);
            System.out.println("..done");
            in.close();
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
    public static String addPadding(String s) {
        int padding = 8 - s.length();
        String paddingString = "";
        for (int j = 0; j < padding; j++) {
            paddingString += "0";
        }
        s = paddingString + s;
        return s;
    }
    
    /**
     * @return juurinode
     */
    public Node buildTree() {
        char next = chars[index];
        if (next == '1') {
            String s = "";
            int j;
            for (j = index + 1; j < index + 9; j++) {
                s += chars[j];
            }
            byte b = Byte.parseByte(s, 2);
            Node node = new Node(1, true);
            node.setByteValue(b);
            index = j;
            return node;
        } else {
            Node node = new Node(1, false);
            index++;
            node.setLeftChild(buildTree());
            node.setRightChild(buildTree());
            return node;
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
    
    public byte[] getOutput(Table<Byte, String> table, Node root) {
        byte[] output = new byte[originalDataLength];
        int index = 0;
        
        char[] data1 = compressedData.toCharArray();
        char[] data = new char[compressedData.length()];
        
        for (int i = 0; i < compressedData.length(); i++) {
            data[i] = data1[i];
        }
        
        int arrIndex = 0;
        int j = 0;
        for (int i = 0; i < originalDataLength; i++) {
            Node current = root;
            String s = "";
            while (!current.getIsLeaf()) {
                char ch = data[j];
                s += ch;
                j++;
                if (ch == '0') {
                    current = current.getLeftChild();
                } else {
                    current = current.getRightChild();
                }
            }
            
            output[arrIndex] = table.getKey(s);
            arrIndex++;
        }
        
        return output;
    }
    
}

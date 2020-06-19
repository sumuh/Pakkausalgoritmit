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
    private File inputFile;
    
    public Decompression(File inputFile, String filetype) {
        this.inputFile = inputFile;
        this.filetype = filetype;
    }
    
    /**
     * Purkaa konstruktorin parametrina saadun tiedoston
     * 
     * @return dekompressoitu tiedosto
     */
    public File decompress() {
        
        readFile(inputFile);
        
        chars = treeString.toCharArray();
        index = 0;
        
        Node root = new Node(1, false);
        
        root = buildTree();
        
        Table<Byte, String> table = new Table(5);
        
        buildTable(table, root, 2, "");
        
        byte[] output = getOutput(table, root);
        
        String fileName = inputFile.getName();
        String[] split = fileName.split("\\_");
        
        File retFile = new File("huffmanfiles/" + split[0] + "_decompressed" + filetype);
        
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(retFile);
            out.write(output);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return retFile;
    }
    
    /**
     * Lukee tiedostosta ensin kolme otsaketta ja sen jälkeen lopun datan formatData-metodin avulla
     * 
     * @param file 
     */
    public void readFile(File file) {
        FileInputStream in = null;
        
        try {
            in = new FileInputStream(file);
            
            // lue otsakkeet
            byte[] bytes = new byte[10];
            in.read(bytes);
            originalDataLength = ByteBuffer.wrap(bytes).getInt();
            
            byte[] bytes2 = new byte[10];
            in.read(bytes2);
            int dataLength = ByteBuffer.wrap(bytes2).getInt();
            
            byte[] bytes3 = new byte[10];
            in.read(bytes3);
            int treeStringLength = ByteBuffer.wrap(bytes3).getInt();
            
            // lue Huffman-puun string-esitys ja varsinainen tiivistetty data
            treeString = formatData(in, treeStringLength);
            compressedData = formatData(in, dataLength);
            in.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Muodostaa datasta 8-bitin "tavuja", jotka palauttaa stringissä
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
     * Tekee syötteessä annetusta stringistä "8-bittisen" eli lisää tarvittaessa nollia
     * 
     * @param s
     * @return s
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
     * Rakentaa tiedostosta luetun Huffman-puun string-esityksen mukaan varsinaisen puun rakenteen
     * 
     * @return juurinode
     */
    public Node buildTree() {
        char next = chars[index];
        if (next == '1') {
            String s = "";
            int j;
            
            for (j = index + 1; j < index + 10; j++) {
                s += chars[j];
            }
            byte b;
            // onko pos vai neg
            System.out.println(s.charAt(0));
            if (s.charAt(0) == '1') {
                System.out.println("neg");
                char[] ninebits = s.toCharArray();
                String s2 = "";
                for (int i = 1; i < 9; i++) {
                    s2 += ninebits[i];
                }
                b = (byte) Integer.parseInt(s2, 2);
                b *= -1;
                System.out.println(b);
            } else {
                System.out.println("pos");
                char[] ninebits = s.toCharArray();
                String s2 = "";
                for (int i = 1; i < 9; i++) {
                    s2 += ninebits[i];
                }
                b = (byte) Integer.parseInt(s2, 2);
                System.out.println(b);
            }
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
     * Rakentaa kääntämisessä käytettävän Huffman-tablen
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
     * Hoitaa varsinaisen purkamisen. Lukee tiivistettyä dataa ja purkaa sen Huffman-tablen ja puun avulla.
     * 
     * @param table
     * @param root
     * @return 
     */
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
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

//        Random random = new Random();
//        byte[] input = new byte[10000];
//        for (int i = 0; i < input.length; i++) {
//            input[i] = (byte) random.nextInt(10);
//        }
//        byte[] input2 = new byte[]{6, 6, 2, 4, 3};
//        
        File txt = new File("huffmanfiles/textfile.txt");
        try {
            FileWriter fw = new FileWriter(txt);
            Random rand = new Random();
            for (int i = 0; i < 100000; i++) {
                fw.write("kl");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Compression c = new Compression(txt);
        File compressed = c.compress();
        Decompression d = new Decompression(compressed, ".txt");
        File decompressed = d.decompress();
    }
    
}

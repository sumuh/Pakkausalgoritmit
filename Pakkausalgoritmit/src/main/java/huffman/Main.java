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
//        
        File text = new File("huffmanfiles/text.txt");
        try {
            FileWriter fw = new FileWriter(text);
            Random rand = new Random();
            for (int i = 0; i < 10000; i++) {
                int n = rand.nextInt(50);
                fw.write("" + n);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Compression c = new Compression(text);
        File compressed = c.compress();
        Decompression d = new Decompression(compressed, ".txt");
        File decompressed = d.decompress();
    }
    
}

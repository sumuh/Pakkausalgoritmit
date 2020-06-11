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
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Susanna Muhli
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File txt = new File("huffmanfiles/text.txt");
        
        // n: suurin määrä kilotavuja mitä tiedostoon kirjoitetaan.
        // esim. jos n = 100 niin ohjelmaa testataan 1 kt, 10 kt ja 100 kt tiedostokoolla
        int n = 100;
        long t;
        
        long[] timesC = new long[n];
        long[] timesD = new long[n];
        
        
        // testataan compression-metodia
        int index1 = 0;
        for (int i = 1; i <= n; i *= 10) {
            writeToFile(txt, i);
            t = System.nanoTime();
            Compression c = new Compression(txt);
            c.compress();
            t = System.nanoTime() - t;
            timesC[index1] = t;
            index1++;
        }
        
        System.out.println("Huffman compression:");
        
        int index2 = 0;
        for (int i = 1; i <= n; i *= 10) {
            System.out.println(i + " kt: " + timesC[index2] / 1000000.0 + " ms");
            index2++;
        }
        
        // testataan decompression-metodia
        int index3 = 0;
        for (int i = 1; i <= n; i *= 10) {
            writeToFile(txt, i);
            Compression c = new Compression(txt);
            File compressed = c.compress();
            t = System.nanoTime();
            Decompression d = new Decompression(compressed, ".txt");
            d.decompress();
            t = System.nanoTime() - t;
            timesD[index3] = t;
            index3++;
        }
        
        System.out.println("Huffman decompression:");
        
        int index4 = 0;
        for (int i = 1; i <= n; i *= 10) {
            System.out.println(i + " kt: " + timesD[index4] / 1000000.0 + " ms");
            index4++;
        }
    }
    
    public static void writeToFile(File file, int n) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            byte[] bytes = new byte[n * 1000];
            Random rand = new Random();
            for (int i = 0; i < n * 1000; i++) {
                bytes[i] = (byte) rand.nextInt(20);
            }
            out.write(bytes);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

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
        File file = new File("bronte40.txt");
        System.out.println("LZW: 40 kt");
        fileTest(file);
        
        File file2 = new File("bronte100.txt");
        System.out.println("LZW: 100 kt");
        fileTest(file2);
    }
    
    // testaa olemassaolevan filen perusteella
    public static void fileTest(File file) {
        
        int n = 10;
        long[] times = new long[n];
        long t;
        for (int i = 0; i < n; i++) {
            t = System.nanoTime();
            Compression c = new Compression(file);
            c.compress();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        Arrays.sort(times);
        long avg = getAverage(times);
        System.out.println("average compression time: " + avg / 1000000.0 + " ms");
        
        times = new long[n];
        for (int i = 0; i < n; i++) {
            Compression c = new Compression(file);
            File compressed = c.compress();
            t = System.nanoTime();
            Decompression d = new Decompression(compressed);
            d.decompress();
            t = System.nanoTime() - t;
            times[i] = t;
        }
        Arrays.sort(times);
        avg = getAverage(times);
        System.out.println("average decompression time: " + avg / 1000000.0 + " ms");
    }
    
    public static long getAverage(long[] arr) {
        long sum = 0;
        for (long l : arr) {
            sum += l;
        }
        return sum / (long) arr.length;
    }
    
    // luo tiedoston jossa on satunnaisia lukuja ja testaa sen tiivistämistä ja purkamista
    public static void randomTest() {
        File txt = new File("lzwfiles/text.txt");
        
        // n: suurin määrä kilotavuja mitä tiedostoon kirjoitetaan.
        // esim. jos n = 100 niin ohjelmaa testataan (noin) 1 kt, 10 kt ja 100 kt tiedostokoolla
        int n = 1000;
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
        
        System.out.println("Lempel-Ziv-Welch compression:");
        
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
            Decompression d = new Decompression(compressed);
            d.decompress();
            t = System.nanoTime() - t;
            timesD[index3] = t;
            index3++;
        }
        
        System.out.println("Lempel-Ziv-Welch decompression:");
        
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

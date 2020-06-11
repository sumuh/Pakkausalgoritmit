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
        
        File txt = new File("lzwfiles/textfile.txt");
        
        // n: suurin määrä kilotavuja mitä tiedostoon kirjoitetaan.
        // esim. jos n = 100 niin ohjelmaa testataan (noin) 1 kB, 10 kB ja 100 kB tiedostokoolla
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
            FileWriter fw = new FileWriter(file);
            while (file.length() < n * 1000) {
                fw.write(loremIpsum());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static char[] randomChar(int size) {
        char[] arr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'
        , 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        char[] ret = new char[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int n = rand.nextInt(arr.length);
            ret[i] = arr[n];
        }
        return ret;
    }
    
    public static String loremIpsum() {
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In convallis sed libero ut eleifend. Cras finibus nunc erat, ut tristique eros fringilla euismod. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer at tempus massa, et varius eros. Mauris dignissim arcu ut ipsum venenatis, nec hendrerit quam elementum. Vestibulum ac elementum enim, non hendrerit purus. Aliquam vitae sem vitae metus tempus finibus. Cras sed quam in odio ornare finibus quis quis sem. Morbi lobortis eros id leo cursus varius. Sed sed ipsum quis nisi venenatis gravida a nec arcu. Donec condimentum sem nec consequat feugiat.";
        String s2 = "Lorem ipsum dolor sit amet";
        return s2;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import huffman.Compression;
import java.io.File;
import java.util.Scanner;

/**
 * Käyttöliittymäkoodi jonka avulla mahdollista purkaa ja tiivistää tiedostoja tekstikäyttöliittymän kautta
 * 
 * @author Susanna Muhli
 */
public class Ui {
    
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("Tervetuloa tiivistämään ja purkamaan tiedostoja\n");
        while (true) {
            System.out.print("Valitse:\n"
                    + "h jos haluat käyttää Huffman-algoritmia\n"
                    + "l jos haluat käyttää Lempel-Ziw-Welch-algoritmia\n"
                    + "c jos haluat sulkea ohjelman\n");
            String choice = in.nextLine();
            if (choice.equals("c")) {
                System.exit(0);
            } else if (choice.equals("h")) {
                System.out.println("--Huffman--");
                System.out.println("Syötä kompressoitavan tiedoston polku:");
                String path = in.nextLine();
                File input = new File(path);
                if (!input.exists()) {
                    System.out.println("Tiedostoa ei ole olemassa!");
                } else {
                    huffmanCompression(input, in);
                }
            } else if (choice.equals("l")) {
                System.out.println("--Lempel-Ziv-Welch--");
                System.out.println("Syötä kompressoitavan tiedoston polku:");
                String path = in.nextLine();
                File input = new File(path);
                if (!input.exists()) {
                    System.out.println("Tiedostoa ei ole olemassa!");
                } else {
                    lzwCompression(input, in);
                }
            }
            System.out.println("");
        }
        
        
    }
    
    public void huffmanCompression(File input, Scanner in) {
        System.out.println("Tiivistettävän tiedoston koko on " + input.length() / 1000 + " kt");
        System.out.print("Tiivistetään...");
        
        long t = System.nanoTime();
        huffman.Compression c = new huffman.Compression(input);
        File compressed = c.compress();
        t = System.nanoTime() - t;
        
        System.out.print(" valmis\n");
        System.out.println("Aikaa kului " + t / 1000000.0 + " ms");
        System.out.println("Tiivistetyn tiedoston nimi on " + compressed.getName());
        System.out.println("Tiivistetyn tiedoston koko on " + compressed.length() / 1000 + " kt");
        System.out.println("");
        System.out.println("Haluatko purkaa tiivistetyn tiedoston?\ny/n");
        String choice = in.nextLine();
        if (choice.equals("y")) {
            huffmanDecompression(compressed, in);
        }
    }
    
    public void huffmanDecompression(File input, Scanner in) {
        System.out.print("Puretaan...");
        String fileName = input.getName();
        String[] split = fileName.split("\\.");
        
        long t = System.nanoTime();
        //huffman.Decompression d = new huffman.Decompression(input, "." + split[1]);
        huffman.Decompression d = new huffman.Decompression(input, ".txt");
        File decompressed = d.decompress();
        t = System.nanoTime() - t;
        
        System.out.print(" valmis\n");
        System.out.println("Aikaa kului " + t / 1000000.0 + " ms");
        System.out.println("Puretun tiedoston nimi on " + decompressed.getName());
        System.out.println("Puretun tiedoston koko on " + decompressed.length() / 1000 + " kt");
    }
    
    public void lzwCompression(File input, Scanner in) {
        System.out.println("Tiivistettävän tiedoston koko on " + input.length() / 1000 + " kt");
        System.out.print("Tiivistetään...");
        
        long t = System.nanoTime();
        lzw.Compression c = new lzw.Compression(input);
        File compressed = c.compress();
        t = System.nanoTime() - t;
        
        System.out.print(" valmis\n");
        System.out.println("Aikaa kului " + t / 1000000.0 + " ms");
        System.out.println("Tiivistetyn tiedoston nimi on " + compressed.getName());
        System.out.println("Tiivistetyn tiedoston koko on " + compressed.length() / 1000 + " kt");
        System.out.println("");
        System.out.println("Haluatko purkaa tiivistetyn tiedoston?\ny/n");
        String choice = in.nextLine();
        if (choice.equals("y")) {
            lzwDecompression(compressed, in);
        }
    }
    
    public void lzwDecompression(File input, Scanner in) {
        System.out.print("Puretaan...");
        String fileName = input.getName();
        String[] split = fileName.split("\\.");
        
        long t = System.nanoTime();
        lzw.Decompression d = new lzw.Decompression(input);
        File decompressed = d.decompress();
        t = System.nanoTime() - t;
        
        System.out.print(" valmis\n");
        System.out.println("Aikaa kului " + t / 1000000.0 + " ms");
        System.out.println("Puretun tiedoston nimi on " + decompressed.getName());
        System.out.println("Puretun tiedoston koko on " + decompressed.length() / 1000 + " kt");
    }
    
}

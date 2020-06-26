/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import tietorakenteet.Table;

/**
 * Tiivistää tekstitiedostoja Lempel-Ziv-Welch-algoritmin avulla
 * 
 * @author Susanna Muhli
 */
public class Compression {
    
    private Table<String, String> dict;
    private File inputFile;
    private byte[] fileContent;
    
    public Compression(File inputFile) {
        this.inputFile = inputFile;
        this.dict = new Table(10);
    }
    
    /**
     * Tiivistää annetun tiedoston
     * 
     * @return tiivistetty tiedosto
     */
    public File compress() {
        
        try {
            fileContent = Files.readAllBytes(inputFile.toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        //alustetaan dictionary arvoilla 0-255
        for (int i = 0; i < 256; i++) {
            String key = intTo16Bit(i);
            dict.add("" + (char) i, key);
        }
        
        String fileName = inputFile.getName();
        String[] split = fileName.split("\\.");
        File retFile = new File("lzwfiles/" + split[0] + "_compressed.bin");
        
        writeToFile(retFile);
        
        return retFile;
        
    }
    
    /**
     * Hoitaa varsinaisen tiivistämisen
     * 
     * @param retFile 
     */
    public void writeToFile(File retFile) {
        
        int keyIndex = 256;
        String s = "";
        char c;
        char ch = (char) fileContent[0];
        s += ch;
        
        try {
            
            FileOutputStream out = new FileOutputStream(retFile);
            DataOutputStream dout = new DataOutputStream(out);
            
            for (int i = 1; i < fileContent.length; i++) {
                c = (char) fileContent[i];
                String sc = s + c;
                if (dict.getLzw(sc) == null) {
                    // koska koodit ovat 16 bitin pituisia ne on kätevää kirjoittaa tiedostoon shorteina
                    dout.writeShort(Short.parseShort(dict.get(s), 2));
                    dict.add(sc, intTo16Bit(keyIndex));
                    keyIndex++;
                    s = "" + c;
                } else {
                    s = sc;
                }
            }

            dout.writeShort(Short.parseShort(dict.get(s), 2));
            dout.flush();
            dout.close();
            out.flush();
            out.close();
            
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Tekee integeristä "16-bittisen" stringin dictionarya varten. Static koska myös Decompression-luokka käyttää tätä.
     * 
     * @param i
     * @return string, jossa 16-bittinen esitys parametrista
     */
    public static String intTo16Bit(int i) {
        String s = Integer.toBinaryString(i);
        StringBuilder sb = new StringBuilder();
        int zeroes = 16 - s.length();
        for (int j = 0; j < zeroes; j++) {
            sb.append(0);
        }
        for (int j = zeroes; j < 16; j++) {
            sb.append(s.charAt(j - zeroes));
        }
        return sb.toString();
    }
    
}

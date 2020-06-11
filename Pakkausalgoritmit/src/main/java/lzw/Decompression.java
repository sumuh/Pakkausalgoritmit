/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import tietorakenteet.Table;

/**
 * Purkaa tiedostoja Lempel-Ziv-Welch-algoritmilla
 * 
 * @author Susanna Muhli
 */
public class Decompression {
    
    private Table<String, String> dict;
    private File inputFile;
    
    public Decompression(File inputFile) {
        this.inputFile = inputFile;
        this.dict = new Table(10);
    }
    
    /**
     * 
     * @return purettu tiedosto
     */
    public File decompress() {
        
        // alustetaan dictionary
        for (int i = 0; i < 256; i++) {
            String key = Compression.intTo12Bit(i);
            dict.add(key, "" + (char) i);
        }
        
        String fileName = inputFile.getName();
        String[] split = fileName.split("\\_");
        File retFile = new File("lzwfiles/" + split[0] + "_decompressed.txt");
        
        readAndWriteToFile(inputFile, retFile);
        
        return retFile;
        
    }
    
    /**
     * Hoitaa varsinaisen tiedoston lukemisen ja purkamisen
     * @param inputFile
     * @param retFile 
     */
    public void readAndWriteToFile(File inputFile, File retFile) {
        
        try {
            int keyIndex = 256;
        
            String oldCode = "";
            String newCode = "";
            String s = "";
            String c = "";
        
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            
            FileWriter fw = new FileWriter(retFile);
            BufferedWriter bw = new BufferedWriter(fw);
            
            oldCode = br.readLine();
            bw.write(dict.get(oldCode));
            
            while ((newCode = br.readLine()) != null) {
                if (dict.getLzw(newCode) == null) {
                    s = dict.get(oldCode);
                    s = s + c;
                } else {
                    s = dict.get(newCode);
                }
                bw.write(s);
                c = "";
                c += s.charAt(0);
                dict.add(Compression.intTo12Bit(keyIndex), dict.get(oldCode) + c);
                keyIndex++;
                oldCode = newCode;
            }
            
            fw.flush();
            bw.flush();
            fr.close();
            fw.close();
            br.close();
            bw.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}

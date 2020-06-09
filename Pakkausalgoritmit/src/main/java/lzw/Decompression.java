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
 *
 * @author Susanna Muhli
 */
public class Decompression {
    
    private Table<String, String> dict;
    private File compressedFile;
    
    public Decompression(File file) {
        this.compressedFile = file;
        this.dict = new Table(10);
    }
    
    /**
     * 
     * @return purettu tiedosto
     */
    public File decompress() {
        
        // alustetaan table
        
        for (int i = 0; i < 256; i++) {
            String key = intTo12Bit(i);
            dict.add(key, "" + (char) i);
        }
        
        
        int keyIndex = 256;
        
        String oldCode = "";
        String newCode = "";
        String s = "";
        String c = "";
        File retFile = new File("lzwfiles/decompressed.txt");
        
        try {
            FileReader fr = new FileReader(compressedFile);
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
                dict.add(intTo12Bit(keyIndex), dict.get(oldCode) + c);
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
        
        return retFile;
        
    }
    
    /**
     * 
     * @param i
     * @return string, jossa 12-bittinen esitys parametrista
     */
    public String intTo12Bit(int i) {
        String s = Integer.toBinaryString(i);
        StringBuilder sb = new StringBuilder();
        int zeroes = 12 - s.length();
        for (int j = 0; j < zeroes; j++) {
            sb.append(0);
        }
        for (int j = zeroes; j < 12; j++) {
            sb.append(s.charAt(j - zeroes));
        }
        return sb.toString();
    }
    
    
}

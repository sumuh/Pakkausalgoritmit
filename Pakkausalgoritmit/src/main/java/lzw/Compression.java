/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import tietorakenteet.Table;

/**
 *
 * @author Susanna Muhli
 */
public class Compression {
    
    private Table<String, String> dict;
    private File file;
    private byte[] fileContent;
    
    public Compression(File file) {
        this.file = file;
        this.dict = new Table(4096);
    }
    
    /**
     * 
     * @return tiivistetty tiedosto
     */
    public File compress() {
        
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        //alustetaan table arvoilla 0-255
        
        for (int i = 0; i < 256; i++) {
            String key = intTo12Bit(i);
            dict.add(key, ""+(char) i);
        }
        
        int keyIndex = 256;
        String s = "";
        char c;
        char ch = (char) fileContent[0];
        s += ch;
        
        File retFile = new File("lzwfiles/compressed.bin");
        
        try {
            //ongelmia tulee jos 2 ensimmäistä kirjainta samat
            FileWriter fw = new FileWriter(retFile);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.flush();
            
            for (int i = 1; i < fileContent.length; i++) {
                c = (char) fileContent[i];
                String sc = s + c;
                if (dict.getKey(sc) == null) {
                    bw.write(dict.getKey(s));
                    bw.newLine();
                    dict.add(intTo12Bit(keyIndex), sc);
                    keyIndex++;
                    s = ""+c;
                } else {
                    s = sc;
                }
            }

            bw.write(dict.getKey(s));
            
            fw.flush();
            bw.flush();
            fw.close();
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
            sb.append(s.charAt(j-zeroes));
        }
        return sb.toString();
    }
    
}

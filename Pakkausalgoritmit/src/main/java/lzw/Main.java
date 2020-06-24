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

/**
 *
 * @author Susanna Muhli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File txt = new File("lzwfiles/bronte.txt");
//        try {
//            FileWriter fw = new FileWriter(txt);
//            for (int i = 0; i < 100000; i++) {
//                fw.write("abbaccab");
//            }
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        
        Compression c = new Compression(txt);
        File f = c.compress();
        Decompression d = new Decompression(f);
        d.decompress();
    }
    
}

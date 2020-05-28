/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Iterator;

/**
 * HashMapin kaltainen rakenne, mutta avaimeen liittyy vain yksi arvo
 *
 * @author Susanna Muhli
 * @param <K>
 * @param <V>
 */
public class Table<K, V> {
    
    private Entry<K, V>[] entries;
    private int size;
    
    public Table(int capacity) {
        entries = new Entry[capacity];
        this.size = 0;
    }
    
    /**
     * lisää tableen avain-arvo-parin
     * 
     * @param key
     * @param value 
     */
    public void add(K key, V value) {
        Entry<K, V> entry = new Entry(key, value);
        int hash = key.hashCode();
        int index = hash % entries.length;
        index = index > 0 ? index : -index;
        
        Entry<K, V> entryAtIndex = entries[index];
        if (entryAtIndex == null) {
            entries[index] = entry;
            size++;
        } else {
            while (true) {
                if (key == entryAtIndex.key) {
                    entryAtIndex.value = value;
                    break;
                }
                if (entryAtIndex.next == null) {
                    entryAtIndex.next = entry;
                    size++;
                    break;
                }
                entryAtIndex = entryAtIndex.next;
            }
        }
    }
    
    
    /**
     * 
     * @param key
     * @return avainta vastaava arvo 
     */
    public V get(K key) {
        int hash = key.hashCode() % entries.length;
        Entry<K, V> entry = entries[hash];
        if (entry.key == key) {
            return entry.value;
        }
        while (entry.next != null) {
            if (entry.next.key == key) {
                return entry.next.value;
            } else {
                entry = entry.next;
            }
        }
        return null;
    }
    
    /**
     * 
     * @return montako avain-arvo-paria tablessa on 
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * 
     * @return tablen tiedot
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                Entry<K, V> entry = entries[i];
                s += entry + "\n";
                while (entry.next != null) {
                    s += entry.next + "\n";
                    entry = entry.next;
                }
            }
        }
        return s;
    }
    
}

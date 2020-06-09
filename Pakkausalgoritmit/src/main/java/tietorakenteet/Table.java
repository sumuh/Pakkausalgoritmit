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
public class Table<K, V> implements Iterable<Entry> {
    
    private Entry<K, V>[] entries;
    private int size;
    private double loadFactor;
    private int capacity;
    
    public Table(int initialCapacity) {
        this.capacity = initialCapacity;
        this.entries = new Entry[capacity];
        this.size = 0;
        this.loadFactor = 0.75;
    }
    
    /**
     * lisää tableen avain-arvo-parin
     * 
     * @param key
     * @param value 
     */
    public void add(K key, V value) {
        Entry<K, V> entry = new Entry(key, value);
        Entry<K, V> entryAtIndex = entries[hash(key)];
        if (entryAtIndex == null) {
            entries[hash(key)] = entry;
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
        
        // kasvata kokoa tarvittaessa
        if ((double) this.size / (double) this.capacity > loadFactor) {
            resize();
        }
        
    }
    
    /**
     * 
     * @param key
     * @return avainta vastaava arvo 
     */
    public V get(K key) {
        Entry<K, V> entry = entries[hash(key)];
        if (entry.key.equals(key)) {
            return entry.value;
        }
        
        while (entry.next != null) {
            if (entry.next.key.equals(key)) {
                return entry.next.value;
            } else {
                entry = entry.next;
            }
        }
        return null;
    }
    
    /**
     * Versio get-metodista jossa palautetaan null myös jos avainta vastaavaa entryä ei ole vielä olemassa
     * 
     * @param key
     * @return avainta vastaava arvo 
     */
    public V getLzw(K key) {
        Entry<K, V> entry = entries[hash(key)];
        if (entry == null) {
            return null;
        }
        if (entry.key.equals(key)) {
            return entry.value;
        }
        while (entry.next != null) {
            if (entry.next.key.equals(key)) {
                return entry.next.value;
            } else {
                entry = entry.next;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param value avaimen arvo
     * @return avain
     */
    public K getKey(V value) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            Entry e = (Entry) it.next();
            if (e.getValue().equals(value)) {
                return (K) e.getKey();
            }
        }
        return null;
    }
    
    /**
     * 
     * @param key
     * @return hash-arvo avaimelle
     */
    public int hash(K key) {
        int hash = key.hashCode() % capacity;
        hash = hash > 0 ? hash : -hash;
        return hash;
    }
    
    /**
     * 
     * tuplaa tablen koon
     */
    public void resize() {
        int newCapacity = this.capacity * 2;
        Entry[] newEntries = new Entry[newCapacity];
        
        for (Entry e : this) {
            
            K key = (K) e.key;
            V value = (V) e.value;
            Entry<K, V> entry = new Entry(key, value);
            int hash = key.hashCode();
            int index = hash % newCapacity;
            index = index > 0 ? index : -index;

            Entry<K, V> entryAtIndex = newEntries[index];
            if (entryAtIndex == null) {
                newEntries[index] = entry;
            } else {
                while (true) {
                    if (key == entryAtIndex.key) {
                        entryAtIndex.value = value;
                        break;
                    }
                    if (entryAtIndex.next == null) {
                        entryAtIndex.next = entry;
                        break;
                    }
                    entryAtIndex = entryAtIndex.next;
                }
            }
        }
        
        this.capacity = newCapacity;
        this.entries = newEntries;
        
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
                s += entry;
                while (entry.next != null) {
                    s += entry.next;
                    entry = entry.next;
                }
            }
        }
        return s;
    }
    
    /**
     * 
     * @return iterator forEach-metodia jne. varten
     */
    @Override
    public Iterator<Entry> iterator() {
        Iterator<Entry> listIterator = new Iterator<Entry>() {

            private int index = 0;
            private int remaining = getSize();
            private Entry next;

            @Override
            public boolean hasNext() {
                if (remaining > 0) {
                    return true;
                }
                return false;
            }

            @Override
            public Entry next() {
                Entry e = next;
                while (e == null) {
                    e = entries[index++];
                }
                next = e.next;
                remaining--;
                return e;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return listIterator;
    }
    
}
 
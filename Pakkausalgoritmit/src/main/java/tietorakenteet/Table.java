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
        hash = hash > 0 ? hash : -hash;
        Entry<K, V> entry = entries[hash];
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
        int hash = key.hashCode() % entries.length;
        hash = hash > 0 ? hash : -hash;
        Entry<K, V> entry = entries[hash];
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
    
    public Table reverseTable() {
        Table reverse = new Table(this.getSize());
        for (Entry e : this) {
            reverse.add((K) e.value, (V) e.key);
        }
        return reverse;
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
 
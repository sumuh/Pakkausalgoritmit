/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Objects;

/**
 * Entry Table-luokkaa varten
 * 
 * @author Susanna Muhli
 * @param <K>
 * @param <V>
 */
public class Entry<K, V> {
    
    K key;
    V value;
    Entry<K, V> next;
    
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.key);
        return hash;
    }

    /**
     * 
     * @param obj
     * @return equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entry<?, ?> other = (Entry<?, ?>) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    /**
     * entryn tiedot
     * @return 
     */
    @Override
    public String toString() {
        return "Key: " + key + ", value: " + value + "\n";
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    
    
    
}

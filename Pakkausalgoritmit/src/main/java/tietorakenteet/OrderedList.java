/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayListin kaltainen lista
 *
 * @author Susanna Muhli
 * @param <E>
 */
public class OrderedList<E> implements Iterable<E> {
    
    private Object list[];
    private int size;
    
    public OrderedList() {
        this.list = new Object[10];
        this.size = 0;
    }
    
    /**
     * lisää listalle uuden arvon
     * 
     * @param e 
     */
    public void add(E e) {
        if (size == list.length - 1) {
            doubleSize();
        }
        list[size] = e;
        size++;
    }
    
    /**
     * poistaa listalta indeksissä i olevan arvon
     * 
     * @param i 
     */
    public void remove(int i) {
        checkBounds(i);
        size--;
        for (int j = i; j < size; j++) {
            list[j] = list[j + 1];
        }
        list[size] = null;
    }
    
    /**
     * 
     * @param i
     * @return indeksissä i oleva arvo
     */
    public E get(int i) {
        checkBounds(i);
        return (E) list[i];
        
    }
    
    /**
     * tuplaa listan koon
     */
    public void doubleSize() {
        Object newList[] = new Object[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
    
    /**
     * 
     * @return montako objektia listalla on
     */
    public int length() {
        return this.size;
    }
    
    /**
     * 
     * @return listan tiedot 
     */
    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                s += list[i] + "]";
            } else {
                s += list[i] + ", ";
            }
        }
        return s;
    }
    
    /**
     * tarkista onko indeksi sallituissa rajoissa
     * 
     * @param i 
     */
    public void checkBounds(int i) {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");
        }
    }
    
    /**
     * 
     * @return iterator forEach-metodia jne. varten
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> listIterator = new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size && list[index] != null;
            }

            @Override
            public E next() {
                return (E) list[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return listIterator;
    }
    
}

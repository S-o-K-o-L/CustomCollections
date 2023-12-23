package com.structure.classes;

import com.structure.interfaces.HashMapCustom;

public class HashMapCustomImpl<K, V> implements HashMapCustom<K, V> {
    private static final int INITIAL_CAPACITY = 101;
    private final float LOAD_FACTOR = 0.75F;

    private int currentCAPACITY;
    private int size;
    private Entry<K, V>[] hashTable;

    public HashMapCustomImpl() {
        hashTable = new Entry[INITIAL_CAPACITY];
        currentCAPACITY = INITIAL_CAPACITY;
        size = 0;
    }

    public HashMapCustomImpl(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater then 0!");
        }
        hashTable = new Entry[capacity];
        currentCAPACITY = capacity;
        size = 0;
    }

    public int size() {
        return size;
    }

    private int hash(int hashCode) {
        return Math.abs(hashCode) % currentCAPACITY;
    }

    private void expandCapacity() {
        Entry<K, V>[] newHashTable = new Entry[currentCAPACITY * 2];
        currentCAPACITY *= 2;
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != null) {
                while (hashTable[i].getNext() != null) {
                    putToNewHashMap(newHashTable, hashTable[i]);
                    hashTable[i] = hashTable[i].getNext();
                }
                putToNewHashMap(newHashTable, hashTable[i]);
            }
        }
        hashTable = newHashTable;
    }

    private void putToNewHashMap(Entry<K, V>[] newHashTable, Entry<K, V> entryToCopy) {
        int index = hash(entryToCopy.getKey().hashCode());
        Entry<K, V> entry = newHashTable[index];
        Entry<K, V> entryToInsert =
                new Entry<>(entryToCopy.getKey(), entryToCopy.getValue(),
                        null, null);
        if (entry == null) {
            newHashTable[index] = entryToInsert;
        } else {
            while (entry.getNext() != null) {
                entry = entry.getNext();
            }
            entryToInsert.setPrevious(entry);
            entry.setNext(entryToInsert);
        }
    }

    public void put(K key, V value) {
        if (size / (float) currentCAPACITY > LOAD_FACTOR) {
            expandCapacity();
        }
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry == null) {
            hashTable[index] = new Entry<>(key, value, null, null);
            size++;
        } else {
            if (entry.getKey().equals(key)) {
                return;
            }
            while (entry.getNext() != null) {
                if (entry.getKey().equals(key)) {
                    return;
                }
                entry = entry.getNext();
            }
            Entry<K, V> entryToSet = new Entry<>(key, value, null, entry);
            entry.setNext(entryToSet);
            size++;
        }
    }

    public V get(K key) {
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry == null) {
            return null;
        } else {
            if (entry.getNext() == null) {
                return entry.getValue();
            }
            while (entry.getNext() != null) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                } else {
                    entry = entry.getNext();
                }
            }
            return null;
        }
    }

    public boolean containsKey(K key) {
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry == null) {
            return false;
        } else {
            if (entry.getNext() == null) {
                return true;
            }
            while (entry.getNext() != null) {
                if (entry.getKey().equals(key)) {
                    return true;
                } else {
                    entry = entry.getNext();
                }
            }
            return false;
        }
    }

    public boolean remove(K key) {
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry == null) {
            return false;
        } else {
            if (entry.getNext() == null) {
                hashTable[index] = null;
                return true;
            }
            while (entry.getNext() != null) {
                if (entry.getKey().equals(key)) {
                    if (entry.getNext() == null) {
                        entry = null;
                    } else {
                        if (entry.getPrevious() == null) {
                            hashTable[index] = entry.getNext();
                            entry.setPrevious(hashTable[index]);
                        }
                        Entry<K, V> temp = entry.getPrevious();
                        temp.setNext(entry.getNext());
                        entry.setPrevious(temp);
                    }
                    return true;
                } else {
                    entry = entry.getNext();
                }
            }
            return false;
        }
    }

    public void clear() {
        for (int i = 0; i < currentCAPACITY; i++) {
            if (hashTable[i] != null) {
                hashTable = null;
            }
        }
        size = 0;
    }
}

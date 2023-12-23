package com.structure.classes;

import com.structure.interfaces.HashMapCustom;

public class HashMapCustomImpl<K, V> implements HashMapCustom<K, V> {
    /**
     * Value which used to initialize hashTable in default constructor
     */
    private static final int INITIAL_CAPACITY = 101;
    /**
     * Value which used to expand hashTable when <b>countFilledEntry</b> > 75% of hashTable capacity
     */
    private static final float LOAD_FACTOR = 0.75F;
    /**
     * Capacity of hashTable
     */
    private int currentCapacity;
    /**
     * Count of elements in hashTable
     */
    private int size;
    /**
     * Count filled entries in hashTable
     */
    private int countFilledEntry;

    /**
     * HashTable to store data (pair key-value)
     */
    private Entry<K, V>[] hashTable;

    /**
     * Create hashTable with initial capacity = 101
     */
    public HashMapCustomImpl() {
        hashTable = new Entry[INITIAL_CAPACITY];
        currentCapacity = INITIAL_CAPACITY;
        size = 0;
        countFilledEntry=0;
    }

    /**
     * Create hashTable with given capacity
     * @param capacity given capacity (must be > 1)
     */
    public HashMapCustomImpl(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater then 0!");
        }
        hashTable = new Entry[capacity];
        currentCapacity = capacity;
        size = 0;
        countFilledEntry=0;
    }

    /**
     * @return size of inserted elements in hashTable
     */
    public int size() {
        return size;
    }

    /**
     * @param hashCode inr value returned by hashCode from <b>key</b>
     * @return index in hashTable
     */
    private int hash(int hashCode) {
        return Math.abs(hashCode) % currentCapacity;
    }

    /**
     * Expand capacity by multiply old capacity by 2 <br>
     * Create new hashTable<br>
     * All entries will be copied in new hashTable
     */
    private void expandCapacity() {
        Entry<K, V>[] newHashTable = new Entry[currentCapacity * 2];
        currentCapacity *= 2;
        for (int i = 0; i < countFilledEntry; i++) {
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

    /**
     * @param newHashTable new hashTable with double capacity
     * @param entryToCopy entry to copy in new hashTable
     */
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

    /**
     * Put key-value pair in hash table
     * @param key  is used to calculate position in hashTable
     * @param value value will be stored by key
     */
    public void put(K key, V value) {
        if (size / (float) currentCapacity > LOAD_FACTOR) {
            expandCapacity();
        }
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry == null) {
            hashTable[index] = new Entry<>(key, value, null, null);
            size++;
            countFilledEntry++;
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

    /**
     * Get value by key
     * @param key is used to get value
     * @return value
     */
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

    /**
     * @param key is used to determine the presence of a pair key-value
     * @return <b>true</b> if hashTable contain <b>value</b> by <b>key</b>, either <b>false</b>
     */
    public boolean containsKey(K key) {
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry == null) {
            return false;
        } else {
            if (entry.getKey().equals(key)) {
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

    /**
     * @param key will be used to remove value
     * @return <b>true</b> if hashTable remove <b>value</b> by <b>key</b>, either <b>false</b>
     */
    public boolean remove(K key) {
        int index = hash(key.hashCode());
        Entry<K, V> entry = hashTable[index];
        if (entry != null) {
            if (entry.getNext() == null) {
                hashTable[index] = null;
                size--;
                return true;
            }
            while (entry.getNext() != null) {
                if (entry.getKey().equals(key)) {
                    if (entry.getNext() == null) {
                        entry = null;
                    } else {
                        if (entry.getPrevious() == null) {
                            hashTable[index] = entry.getNext();
                            hashTable[index].setPrevious(null);
                            entry.setPrevious(null);
                            size--;
                            return true;
                        }
                        Entry<K, V> temp = entry.getPrevious();
                        temp.setNext(entry.getNext());
                        entry.setPrevious(temp);
                    }
                    size--;
                    return true;
                } else {
                    entry = entry.getNext();
                }
            }
        }
        return false;
    }

    /**
     * Clear all hashTable from pair key-value
     */
    public void clear() {
        for (int i = 0; i < currentCapacity; i++) {
            if (hashTable[i] != null) {
                hashTable[i] = null;
            }
        }
        size = 0;
    }
}

package com.structure.interfaces;

public interface HashMapCustom<K, V> {
    /**
     * Put key-value pair in hash table
     * @param key  is used to calculate position in hashTable
     * @param value value will be stored by key
     */
    void put(K key, V value);

    /**
     * Get value by key
     * @param key is used to get value
     * @return value
     */
    V get(K key);

    /**
     * @param key is used to determine the presence of a pair key-value
     * @return <b>true</b> if hashTable contain <b>value</b> by <b>key</b>, either <b>false</b>
     */
    boolean containsKey(K key);

    /**
     * @param key will be used to remove value
     * @return <b>true</b> if hashTable remove <b>value</b> by <b>key</b>, either <b>false</b>
     */
    boolean remove(K key);

    /**
     * Clear all hashTable from pair key-value
     */
    void clear();

    /**
     * @return size of inserted elements in hashTable
     */
    int size();
}

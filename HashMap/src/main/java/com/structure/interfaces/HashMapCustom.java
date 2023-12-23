package com.structure.interfaces;

public interface HashMapCustom<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    boolean remove(K key);
    void clear();
    int size();
}

package com.structure.classes;

import java.util.Objects;

public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K,V> next;
    private Entry<K,V> previous;

    public Entry(K key, V value, Entry<K, V> next, Entry<K, V> previous) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    public void setPrevious(Entry<K, V> previous) {
        this.previous = previous;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public Entry<K, V> getPrevious() {
        return previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry<?, ?> entry)) return false;
        return key.equals(entry.key) && value.equals(entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}

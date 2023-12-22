package com.structure.classes;

import com.structure.interfaces.MyList;
import org.jetbrains.annotations.NotNull;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class MyArrayList<T> implements MyList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] array;
    private int size;
    private int currentCapacity;

    /**
     * Create MyArrayList with <b>INITIAL_CAPACITY</b>
     */
    public MyArrayList() {
        array = new Object[INITIAL_CAPACITY];
        size = 0;
        currentCapacity = INITIAL_CAPACITY;
    }

    /**
     * Create MyArrayList with <b>capacity</b>
     */
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Must be greater or equal then 0!");
        }
        array = new Object[capacity];
        this.size = 0;
        currentCapacity = capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(T elem) {
        return indexOf(elem) >= 0;
    }

    public boolean containsAll(@NotNull Collection<T> collection) {
        return collection.stream().allMatch(this::contains);
    }

    public void forEach(@NotNull Consumer<T> action) {
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked") T elem = (T) array[i];
            action.accept(elem);
        }
    }

    public T[] toArray() {
        Object[] arrayCopy = new Object[size];
        System.arraycopy(array, 0, arrayCopy, 0, size);
        @SuppressWarnings("unchecked") T[] arrayClone = (T[]) arrayCopy;
        return arrayClone;
    }

    public void add(@NotNull T elem) {
        addLast(elem);
    }

    public void add(int index, @NotNull T elem) {
        checkValidIndex(index);
        if (currentCapacity == size) {
            expandArray();
        }
        for (int i = 0; i < size; i++) {
            if (index == i) {
                System.arraycopy(array, i, array, i + 1, size - i);
                size++;
                array[i] = elem;
            }
        }
    }

    public void addFirst(@NotNull T elem) {
        if (size == currentCapacity) {
            expandArray();
        }
        System.arraycopy(array, 0, array, 1, size);
        array[0] = elem;
        size++;
    }

    public void addLast(@NotNull T elem) {
        if (size == currentCapacity) {
            expandArray();
        }
        array[size] = elem;
        size++;
    }

    public void addAll(@NotNull Collection<T> collection) {
        collection.forEach(this::addLast);
    }

    public void addAll(int index, @NotNull Collection<T> collection) {
        checkValidIndex(index);
        Object[] objects = collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            @SuppressWarnings("unchecked") T elem = (T) objects[i];
            add(index, elem);
            index++;
        }
    }

    public boolean remove(@NotNull T elem) {
        if (!isEmpty()) {
            int index = indexOf(elem);
            if (index == -1) {
                return false;
            }
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, size - 1);
            size--;
            return true;
        }
        return false;
    }

    public T remove(int index) {
        checkValidIndex(index);
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elemRemoved = (T) array[index];
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, size - 1);
            size--;
            return elemRemoved;
        }
        throw new NoSuchElementException("List is empty!");
    }

    public T removeFirst() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elemRemoved = (T) array[0];
            array[0] = null;
            System.arraycopy(array, 1, array, 0, size - 1);
            size--;
            return elemRemoved;
        }
        throw new NoSuchElementException("List is empty!");
    }


    public T removeLast() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elemRemoved = (T) array[size - 1];
            array[size - 1] = null;
            size--;
            return elemRemoved;
        }
        throw new NoSuchElementException("List is empty!");
    }


    public boolean removeIf(@NotNull Predicate<T> filter) {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                @SuppressWarnings("unchecked") T elem = (T) array[i];
                if (filter.test(elem)) {
                    array[i] = null;
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                    size--;
                }
            }
            return true;
        }
        return false;
    }

    public boolean removeAll(@NotNull Collection<T> collection) {
        return collection.stream().allMatch(this::remove);
    }

    public T get(int index) {
        checkValidIndex(index);
        @SuppressWarnings("unchecked") T elem = (T) array[index];
        return elem;
    }

    public T getFirst() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elem = (T) array[0];
            return elem;
        }
        throw new NoSuchElementException("List is empty!");
    }

    public T getLast() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elem = (T) array[size - 1];
            return elem;
        }
        throw new NoSuchElementException("List is empty!");
    }

    public void set(int index, @NotNull T element) {
        checkValidIndex(index);
        array[index] = element;
    }

    public int indexOf(@NotNull T elem) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(@NotNull T elem) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public MyList<T> subList(int fromIndex, int toIndex) {
        checkValidIndex(fromIndex);
        checkValidIndex(toIndex - 1);
        MyList<T> mySubList = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            @SuppressWarnings("unchecked") T elemToAdd = (T) array[i];
            mySubList.add(elemToAdd);
        }
        return mySubList;
    }

    @SuppressWarnings("unchecked")
    public Stream<T> stream() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return (Stream<T>) Stream.of(newArray);
    }

    @SuppressWarnings("unchecked")
    public MyList<T> reversed() {
        MyArrayList<T> newArrayList = new MyArrayList<>(size);
        for (int i = size - 1; i >= 0;i--) {
            @SuppressWarnings("unchecked") T elem = (T) array[i];
            newArrayList.add(elem);
        }
        return newArrayList;
    }

    @SuppressWarnings("unchecked")
    public void sort(@NotNull Comparator<T> comparator) {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        Arrays.sort((T[]) newArray, comparator);
        System.arraycopy(newArray, 0, array, 0, size);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * If index doesn't satisfy to count of elements in list then exception
     * <b>IndexOutOfBoundsException</b> will be thrown
     */
    private void checkValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index has out of range!");
        }
    }

    /**
     * Expand capacity of list by multiply on 2
     */
    private void expandArray() {
        Object[] newArray = new Object[currentCapacity * 2];
        currentCapacity *= 2;
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyArrayList<?> that)) return false;
        return size == that.size && currentCapacity == that.currentCapacity && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, currentCapacity);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}

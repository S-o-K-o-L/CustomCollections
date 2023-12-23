package com.structure.classes;

import com.structure.interfaces.MyList;
import org.jetbrains.annotations.NotNull;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class MyArrayList<T> implements MyList<T> {
    /**
     * Value which used to initialize list by default constructor
     */
    private static final int INITIAL_CAPACITY = 10;
    /**
     * Array which used to store data
     */
    private Object[] array;
    /**
     * Count of elements in list
     */
    private int size;
    /**
     * Capacity of array
     */
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

    /**
     *Count list elements
     *@return size of list
     */
    public int size() {
        return this.size;
    }

    /**
     * @return <b>true</b> if list is empty, <br>
     *  <b>false</b> if list is not empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @return <b>true</b> if list is contain, <br>
     *         <b>false</b> if list is not contain elem
     */
    public boolean contains(T elem) {
        return indexOf(elem) >= 0;
    }

    /**
     * @return <b>true</b> if list is contain all elements of given collection, <br>
     *         <b>false</b> if list is not contain elem at least one element of given collection
     */
    public boolean containsAll(@NotNull Collection<T> collection) {
        return collection.stream().allMatch(this::contains);
    }

    /**
     * Execute action for each element in list
     */
    public void forEach(@NotNull Consumer<T> action) {
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked") T elem = (T) array[i];
            action.accept(elem);
        }
    }

    /**
     * @return arrays representing of list elements
     */
    public T[] toArray() {
        Object[] arrayCopy = new Object[size];
        System.arraycopy(array, 0, arrayCopy, 0, size);
        @SuppressWarnings("unchecked") T[] arrayClone = (T[]) arrayCopy;
        return arrayClone;
    }

    /**
     * Add not-null element to list
     */
    public void add(@NotNull T elem) {
        addLast(elem);
    }

    /**
     * Add not-null element to list in position <b>index</b>
     */
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

    /**
     * Add not-null element to beginning list
     */
    public void addFirst(@NotNull T elem) {
        if (size == currentCapacity) {
            expandArray();
        }
        System.arraycopy(array, 0, array, 1, size);
        array[0] = elem;
        size++;
    }

    /**
     * Add not-null element to end of list
     */
    public void addLast(@NotNull T elem) {
        if (size == currentCapacity) {
            expandArray();
        }
        array[size] = elem;
        size++;
    }

    /**
     * Add all elements from given collection to end of list
     */
    public void addAll(@NotNull Collection<T> collection) {
        collection.forEach(this::addLast);
    }

    /**
     * Add all elements from given collection to position <b>index</b>
     */
    public void addAll(int index, @NotNull Collection<T> collection) {
        checkValidIndex(index);
        Object[] objects = collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            @SuppressWarnings("unchecked") T elem = (T) objects[i];
            add(index, elem);
            index++;
        }
    }

    /**
     * Remove first element by equals from list
     * @return <b>true</b> if elem was removed, <br>
     *         <b>false</b> if elem wasn't removed
     */
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

    /**
     * Remove first element by <b>index</b> from list
     * @return element was removed
     */
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

    /**
     * Remove first element by equals from list
     * @return element was removed
     */
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

    /**
     * Remove last element by equals from list
     * @return element was removed
     */
    public T removeLast() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elemRemoved = (T) array[size - 1];
            array[size - 1] = null;
            size--;
            return elemRemoved;
        }
        throw new NoSuchElementException("List is empty!");
    }

    /**
     * Remove every element which satisfy to predicate
     * @return <b>true</b> if all element was removed,<br> either
     *         <b>false</b>
     */
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

    /**
     * Remove every element from collection
     * @return <b>true</b> if all element was removed,<br> either
     *         <b>false</b>
     */
    public boolean removeAll(@NotNull Collection<T> collection) {
        return collection.stream().allMatch(this::remove);
    }

    /**
     * @return element from list by index
     */
    public T get(int index) {
        checkValidIndex(index);
        @SuppressWarnings("unchecked") T elem = (T) array[index];
        return elem;
    }

    /**
     * @return first element from list
     */
    public T getFirst() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elem = (T) array[0];
            return elem;
        }
        throw new NoSuchElementException("List is empty!");
    }

    /**
     * @return last element from list
     */
    public T getLast() {
        if (!isEmpty()) {
            @SuppressWarnings("unchecked") T elem = (T) array[size - 1];
            return elem;
        }
        throw new NoSuchElementException("List is empty!");
    }

    /**
     * Set <b>element</b> to <b>index</b> in list
     */
    public void set(int index, @NotNull T element) {
        checkValidIndex(index);
        array[index] = element;
    }

    /**
     * @return first index of elem in list
     */
    public int indexOf(@NotNull T elem) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return last index of elem in list
     */
    public int lastIndexOf(@NotNull T elem) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return new list from <b>fromIndex</b> to <b>toIndex</b>
     */
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

    /**
     * @return stream from array which contains <b>size</b> elements
     */
    @SuppressWarnings("unchecked")
    public Stream<T> stream() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return (Stream<T>) Stream.of(newArray);
    }

    /**
     * @return new list with reversed order of elements
     */
    @SuppressWarnings("unchecked")
    public MyList<T> reversed() {
        MyArrayList<T> newArrayList = new MyArrayList<>(size);
        for (int i = size - 1; i >= 0;i--) {
            @SuppressWarnings("unchecked") T elem = (T) array[i];
            newArrayList.add(elem);
        }
        return newArrayList;
    }

    /**
     * Sort array for given <b>comparator</b>
     */
    @SuppressWarnings("unchecked")
    public void sort(@NotNull Comparator<T> comparator) {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        Arrays.sort((T[]) newArray, comparator);
        System.arraycopy(newArray, 0, array, 0, size);
    }

    /**
     * Clear all elements in list (set null)
     */
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

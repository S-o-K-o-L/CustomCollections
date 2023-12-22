package com.structure.interfaces;

import org.jetbrains.annotations.NotNull;


import java.util.Collection;
import java.util.Comparator;

import java.util.function.Consumer;
import java.util.function.Predicate;

import java.util.stream.Stream;

public interface MyList<T> {
    /**
     *Count list elements
     *@return size of list
     */
    int size();

    /**
     * @return <b>true</b> if list is empty, <br>
     *  <b>false</b> if list is not empty
     */
    boolean isEmpty();

    /**
     *
     * @return <b>true</b> if list is contain, <br>
     *         <b>false</b> if list is not contain elem
     */
    boolean contains(T elem);

    /**
     *
     * @return <b>true</b> if list is contain all elements of given collection, <br>
     *         <b>false</b> if list is not contain elem at least one element of given collection
     */
    boolean containsAll(@NotNull Collection<T> collection);

    /**
     * Execute action for each element in list
     */
    void forEach(@NotNull Consumer<T> action);

    /**
     * @return arrays representing of list elements
     */
    T[] toArray();

    /**
     * Add not-null element to list
     */
    void add(@NotNull T elem);

    /**
     * Add not-null element to list in position <b>index</b>
     */
    void add(int index, @NotNull T elem);

    /**
     * Add not-null element to beginning list
     */
    void addFirst(@NotNull T elem);

    /**
     * Add not-null element to end of list
     */
    void addLast(@NotNull T elem);

    /**
     * Add all elements from given collection to end of list
     */
    void addAll(@NotNull Collection<T> collection);

    /**
     * Add all elements from given collection to position <b>index</b>
     */
    void addAll(int index, @NotNull Collection<T> collection);

    /**
     * Remove first element by equals from list
     * @return <b>true</b> if elem was removed, <br>
     *         <b>false</b> if elem wasn't removed
     */
    boolean remove(@NotNull T elem);

    /**
     * Remove first element by <b>index</b> from list
     * @return element was removed
     *
     */
    T remove(int index);

    /**
     * Remove first element by equals from list
     * @return element was removed
     */
    T removeFirst();

    /**
     * Remove last element by equals from list
     * @return element was removed
     */
    T removeLast();

    /**
     * Remove every element which satisfy to predicate
     * @return <b>true</b> if all element was removed,<br> either
     *         <b>false</b>
     */
    boolean removeIf(@NotNull Predicate<T> filter);

    /**
     * Remove every element from collection
     * @return <b>true</b> if all element was removed,<br> either
     *         <b>false</b>
     */
    boolean removeAll(@NotNull Collection<T> collection);

    /**
     *
     * @return element from list by index
     */
    T get(int index);

    /**
     * @return first element from list
     */
    T getFirst();

    /**
     * @return last element from list
     */
    T getLast();

    /**
     *
     * Set <b>element</b> to <b>index</b> in list
     */
    void set(int index, @NotNull T element);

    /**
     *
     * @return first index of elem in list
     */
    int indexOf(@NotNull T elem);

    /**
     *
     * @return last index of elem in list
     */
    int lastIndexOf(@NotNull T elem);

    /**
     *
     * @return new list from <b>fromIndex</b> to <b>toIndex</b>
     */
    MyList<T> subList(int fromIndex, int toIndex);

    /**
     * @return stream from array which contains <b>size</b> elements
     */
    Stream<T> stream();

    /**
     * @return new list with reversed order of elements
     */
    MyList<T> reversed();

    /**
     * Sort array for given <b>comparator</b>
     */
    void sort(@NotNull Comparator<T> comparator);

    /**
     * Clear all elements in list (set null)
     */
    void clear();
}

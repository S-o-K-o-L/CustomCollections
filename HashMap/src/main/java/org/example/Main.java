package org.example;

import com.structure.classes.HashMapCustomImpl;
import com.structure.classes.MyArrayList;
import com.structure.interfaces.HashMapCustom;
import com.structure.interfaces.MyList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashMapCustom<Integer, MyList<Integer>> integerMyListHashMapCustom = new HashMapCustomImpl<>();
        MyList<Integer> integerMyList = new MyArrayList<>();
        integerMyList.add(1);
        integerMyList.add(2);
        integerMyList.add(3);
        integerMyListHashMapCustom.put(1, integerMyList);
        System.out.println(Arrays.toString(integerMyListHashMapCustom.get(1).toArray()));
    }
}
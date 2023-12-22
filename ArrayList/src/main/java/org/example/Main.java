package org.example;

import com.structure.classes.MyArrayList;
import com.structure.interfaces.MyList;



public class Main {
    public static void main(String[] args) {
        MyList<String> stringMyList = new MyArrayList<>();
        stringMyList.add("123");
        stringMyList.add("456");
        stringMyList.forEach(System.out::println);
    }
}
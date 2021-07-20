package com.example.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    @org.junit.Test
    public void main() {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        //String[] array = new String[list.size()];
        //String[] array = new String[3];
        System.out.println("Hello world!");
        String[] array;
        for (int i = 0; i < list.size(); i++) {
            array = new String[1];
            array = list.toArray(array);
            System.out.println(array);
            System.out.println(array[i]);
        }
        System.out.println(list.toString());
    }
    @org.junit.Test
    public void test() {
        String[] str = new String[] {"you", "wu"};
        List list = Arrays.asList(str);
        System.out.println(list.toString());
        str[0] = "gujin";
        System.out.println(list.toString());
        //list.add("yangguanbao");
    }
    @org.junit.Test
    public void twice() {

    }
}

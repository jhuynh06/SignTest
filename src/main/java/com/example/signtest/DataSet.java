package com.example.signtest;

import java.util.ArrayList;

public class DataSet {
    private static ArrayList<Integer> x;
    private static ArrayList<Integer> y;

    public DataSet (ArrayList<Integer> data1, ArrayList<Integer> data2) {
        x = data1;
        y = data2;
    }
    public static ArrayList<Integer> getData1() {
        return x;
    }
    public static ArrayList<Integer> getData2() {
        return y;
    }
}

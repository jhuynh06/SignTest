package com.example.signtest;

import java.util.ArrayList;

public class DataSet {
    private static ArrayList<Double> x;
    private static ArrayList<Double> y;

    public DataSet (ArrayList<Double> data1, ArrayList<Double> data2) {
        x = data1;
        y = data2;
    }
    public DataSet (ArrayList<Double> data1) {
        x = data1;
    }
    public static ArrayList<Double> getData1() {
        return x;
    }
    public static ArrayList<Double> getData2() {
        return y;
    }
}

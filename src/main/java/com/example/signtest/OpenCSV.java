package com.example.signtest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class OpenCSV {
    private boolean oneSample;
    private boolean oneValue;

    public DataSet readColumns(String path) {
        oneValue = false;
        ArrayList<Double> data1 = new ArrayList<Double>();
        ArrayList<Double> data2 = new ArrayList<Double>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(path));
            String [] nextLine;
            if (oneSample == true) {
                try {
                    List<String> data = Files.readAllLines(Paths.get(path));
                    PrintWriter pw = new PrintWriter(path);
                    FileWriter fw = new FileWriter(path, true);
                    pw = new PrintWriter(fw);
                    for (int i = 0; i < data.size(); i++) {
                        String[] line = data.get(i).split(",");
                        List<String> record = new ArrayList<String>(Arrays.asList(line));
                        record.add(1, "a");
                        pw.println(String.join(",", record));
                    }

                    pw.close();
                    oneValue = true;
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }

            while ((nextLine = csvReader.readNext()) != null) {
                if (oneValue == true) {
                    data1.add(Double.valueOf(nextLine[0]));
                }
                else {
                    data1.add(Double.valueOf(nextLine[0]));
                    data2.add(Double.valueOf(nextLine[1]));
                }
            }
        }
        catch(IOException | CsvValidationException ex) {
            ex.printStackTrace();
        }
        if (oneValue == true) {
            return new DataSet(data1);
        }
        return new DataSet(data1, data2);
    }
    public void setOneSample(boolean a) {
        oneSample = a;
    }
}


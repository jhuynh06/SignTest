package com.example.signtest;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class OpenCSV {

    public DataSet readColumns(String path) {
        ArrayList<Integer> data1 = new ArrayList<Integer>();
        ArrayList<Integer> data2 = new ArrayList<Integer>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(path));
            String [] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                data1.add(Integer.valueOf(nextLine[0]));
                data2.add(Integer.valueOf(nextLine[1]));
            }
        }
        catch(IOException | CsvValidationException ex) {
            ex.printStackTrace();
        }
        return new DataSet(data1, data2);
    }
}


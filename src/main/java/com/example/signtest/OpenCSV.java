package com.example.signtest;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class OpenCSV {

    public DataSet readColumns(String path) {
        ArrayList<Double> data1 = new ArrayList<Double>();
        ArrayList<Double> data2 = new ArrayList<Double>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(path));
            String [] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                data1.add(Double.valueOf(nextLine[0]));
                data2.add(Double.valueOf(nextLine[1]));
            }
        }
        catch(IOException | CsvValidationException ex) {
            ex.printStackTrace();
        }
        return new DataSet(data1, data2);
    }
}


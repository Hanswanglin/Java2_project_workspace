package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * This {@code CSVReader} class represents a reader who read from csv file.
 *
 * @author Pan Tianci
 * @see java.io.BufferedReader
 * @see java.io.FileReader
 * @see javafx.collections.ObservableList
 * @see javafx.collections.FXCollections
 */
public class CSVReader implements FileReader{
    /**
     * read csv file by the target file path.
     *
     * @return all row data as String[].
     */
    public ObservableList<String[]> readData()  {
        ObservableList<String[]> csvData = FXCollections.observableArrayList();
        File csv = new File("src/dataSource/earthquakes.csv");
        BufferedReader br = null;
        try {
            String line = "";
            br = new BufferedReader(new java.io.FileReader(csv));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] var1 = line.split(",");
                csvData.add(var1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }
}

package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * Created by hans on 2017/12/21.
 */

public class CSVReader implements reader {

    public static ObservableList<String[]> readData() {
        final ObservableList<String[]> data = FXCollections.observableArrayList();
        File csv = new File("src/dataSource/earthquakes.csv");  // CSV文件路径
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        try {
            br.readLine();  //读取表头
            while ((line = br.readLine()) != null) {  //读取到的内容给line变量

                String[] l = line.split(",");
                data.add(l);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

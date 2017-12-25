package Controller;

import Dao.CSVReader;
import Dao.DBReader;
import Dao.WebReader;
import bean.quake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * Created by hans on 2017/12/21.
 */
public class DataController {
    String date1;
    String date2;
    String re;
    static int Datacounter;
    double mag;
    private ObservableList<String[]> data;
    ObservableList<quake> result = FXCollections.observableArrayList();

    public DataController(int source){
        switch (source) {
            case 1:
                data = CSVReader.readData();
                break;
            case 2:
                data = DBReader.readData();
                break;
            case 3:
                data = WebReader.readData();
                break;
        }
    }

    public ObservableList<quake> filter(){

        // 绛涢�夋暟鎹�
        for (String[] l : data) {
            quake q = null;
            try {
                q = Filter.filter(date1, date2, mag,re, l);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (q != null) {
                result.add(q);
                Datacounter++;
            }
        }
        return result;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
    public void setRe(String re) {
    	this.re = re;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }
    
    public int getTimes() {
    	return Datacounter;
    }
    public void refresh() {
    	Datacounter=0;
    }
}

package Controller;

import Dao.CSVReader;
import Dao.DBReader;
import Dao.WebReader;
import bean.quake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.TreeSet;

/**
 * Created by hans on 2017/12/21.
 */
public class DataController {
    int source;
    String date1 = "2017-10-10";
    String date2 = "2017-12-15";
    String re = wordWide;
    static int Datacounter;
    float mag;
    private ObservableList<String[]> data;
    ObservableList<quake> result = FXCollections.observableArrayList();

    static TreeSet<String> regions = new TreeSet<>();
    static public String wordWide = "-- World Wide --";
    CSVReader csvReader = new CSVReader();
    DBReader dbReader = new DBReader();
    WebReader webReader = new WebReader();

    public DataController(int source){  // initialize
        this.source = source;
        switch (source) {
            case 1:
                data = csvReader.readData();
                break;
            case 2:
                data = dbReader.readData(date1, date2, mag, re);
                break;
            case 3:
                data = webReader.readData();
                break;
        }
        regions.add(wordWide);
        for(String[] l:data) {
            regions.add(l[6]);
        }
    }

    public static TreeSet<String> getRegions(){
        return regions;
    }

    private ObservableList<quake> filter(){
        result = FXCollections.observableArrayList();
        switch (source){
            case 1:
            case 3:
                for (String[] l : data) {
                    quake q;
                    try {
                        if(Filter.filter(date1,date2,mag,re,l)){
                            q = getQuake(l);
                            result.add(q);
                            Datacounter++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                data = dbReader.readData(date1, date2, mag, re);
                for (String[] l : data) {
                    quake q;
                    q = getQuake(l);
                    result.add(q);
                    Datacounter++;
                }
                break;
        }
        return result;
    }

    private quake getQuake(String[] l) {
        quake q;
        Integer l0 = Integer.parseInt(l[0]);
        String l1 = l[1];
        Float l2 = Float.parseFloat(l[2]);
        Float l3 = Float.parseFloat(l[3]);
        Integer l4 = Integer.parseInt(l[4]);
        Float l5 = Float.parseFloat(l[5]);
        String l6 = l[6].replaceAll("\"", "");
        q = new quake(l0, l1, l2, l3, l4, l5, l6);
        return q;
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

    public void setMag(float mag) {
        this.mag = mag;
    }
    
    public int getTimes() {
    	return Datacounter;
    }
    public void refresh() {
    	Datacounter=0;
        filter();
    }
}

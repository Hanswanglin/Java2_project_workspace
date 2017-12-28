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
 * The {@code DataController} class processing data from data source.
 * <p>It can get data by call the corresponding Reader object, do filter,
 * convert and store them in a ObservableList<quake> object.
 * WholeController can get the result by call the {@link #getResult()} method.</p>
 *
 * @author  Zhang Yilin
 * @author  Pan Tianci
 * @see     WholeController
 * @see     Dao.DBReader
 * @see     Dao.WebReader
 * @see     Dao.CSVReader
 */

public class DataController {
    private int source;
    private String date1;
    private String date2;
    private String re = wordWide;
    private int Datacounter;
    private float mag;
    private ObservableList<String[]> data;

    ObservableList<quake> result = FXCollections.observableArrayList();

    static TreeSet<String> regions = new TreeSet<>();
    static public String wordWide = "-- World Wide --";
    CSVReader csvReader = new CSVReader();
    DBReader dbReader = new DBReader();
    WebReader webReader = new WebReader();

    /**
     * Creates a object to get data from the appointed data source.
     *
     * @param source
     *            The path user choosed to use as the data source.
     */
    public DataController(int source){  // initialize
        this.source = source;
        switch (source) {
            case 1:
                data = csvReader.readData();
                break;
            case 2:
                break;
            case 3:
                data = webReader.readData();
                break;
        }
    }

    /**
     * Add regions to regions set.
     */
    public void loadRegions(){
        regions.add(wordWide);
        for(String[] l:data) {
            regions.add(l[6]);
        }
    }

    /**
     * Gets the set of regions user selectable.
     *
     * @return the set of regions user selectable.
     */
    public static TreeSet<String> getRegions(){
        return regions;
    }

    /**
     * Extract the data that meets the requirements and get their list.
     *
     * <p> Match the data obtained from CSV or Web to the screening conditions,
     * then convert them to the quake type and store in the result set.
     * If the data form database, convert and store them in the result set directly.
     * Count data records.
     *
     * @return the list of data that matches conditions.
     */
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

    /**
     * Convert String[] to quake
     *
     * @param l
     *            A line of records as String[] type.
     * @return the quake Type.
     */
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

    /**
     * Set the start date of a conditional query.
     *
     * @param date1
     *            The start date of a conditional query.
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    /**
     * Set the end date of a conditional query.
     *
     * @param date2
     *            The end date of a conditional query.
     */
    public void setDate2(String date2) {
        this.date2 = date2;
    }

    /**
     * Set the region of a conditional query.
     *
     * @param re
     *            The region of a conditional query.
     */
    public void setRe(String re) {
    	this.re = re;
    }

    /**
     * Set the magnitude of a conditional query.
     *
     * @param mag
     *            The magnitude of a conditional query.
     */
    public void setMag(float mag) {
        this.mag = mag;
    }

    /**
     * Returns the current count of records.
     *
     * @return The current count of records.
     */
    public int getTimes() {
    	return Datacounter;
    }

    /**
     * Refresh the data counter, the data records, and load regions to prevent updates.
     */
    public void refresh() {
    	Datacounter=0;
        filter();
        loadRegions();
    }

    /**
     * Returns the search result.
     *
     * @return The search result.
     */
    public ObservableList<quake> getResult() {
        return result;
    }
}

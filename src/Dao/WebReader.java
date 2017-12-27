package Dao;

import DBUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by hans on 2017/12/21.
 */
public class WebReader {
    public ObservableList<String[]> readData() {
        ObservableList<String[]> webData = FXCollections.observableArrayList();
        ArrayList<String> sql_list = new ArrayList<>();
        try {
            int view = 1;
            for (int i = 0; i < 1; i++) {
                Document doc = Jsoup.connect("https://www.emsc-csem.org/Earthquake/?view=" + view).get();
                view++;
                Elements content = doc.select("#content");
                for (Element con : content) {
                    Elements tables = con.select("table");
                    Element tb = tables.get(1);
                    Elements trs = tb.select("tr");
                    for (Element tr : trs) {
                        Elements cells = tr.select("td");
                        if (cells.size() > 11) {
                            String[] webQuake = new String[7];

                            /*following will gain data by select corresponding element*/
                            String id = tr.id();
                            String year = cells.get(3).select("b a").text();
                            String latitude = cells.get(4).text();
                            String longitude = cells.get(6).text();
                            String depth = cells.get(8).text();
                            String Mag = cells.get(10).text();
                            String region = cells.get(11).text();

                            /*following add quake info into the String[]*/
                            webQuake[0] = id;
                            webQuake[1] = year;
                            webQuake[2] = latitude;
                            webQuake[3] = longitude;
                            webQuake[4] = depth;
                            webQuake[5] = Mag;
                            webQuake[6] = region;
                            webData.add(webQuake);
                            String sql = "insert or ignore into quakes values(" +
                                            webQuake[0]+ ",datetime('" +
                                            webQuake[1]+ "'),case '" +
                                            cells.get(5).text()+"' when 'N' then 1 else -1 end * "+
                                            webQuake[2]+ ",case '"+
                                            cells.get(7).text()+"' when 'W' then -1 else 1 end * "+
                                            webQuake[3]+ ","+
                                            webQuake[4]+ ","+
                                            webQuake[5]+ ",\""+
                                            webQuake[6]+ "\",0)";
                            sql_list.add(sql);
                        }
                    }
                }
            }
            /*//use as test
            for (String s:sql_list) {
                System.out.println(s);
            }*/
            DBUtil dbutil = new DBUtil();
            Connection conn = dbutil.getConnection();
            Statement stat = conn.createStatement();
            for (String sql: sql_list) {
                stat.executeUpdate(sql);
            }
            dbutil.closeDBResource(conn,stat,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webData;
    }
}

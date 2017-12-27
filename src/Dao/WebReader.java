package Dao;

import DBUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This {@code WebReader} class represents a reader who read from Web.
 *
 * <p> WebReader is a reader which retrieve data from the appointed website, there is only
 * one method here, every time the method revoked, we will retrieve the first pages and second
 * page from website and then all relative data will be stored in {@code ObservableList<String[]>}
 * webData.
 * <p> Every time we retrieve one line of data, we generate a corresponding sql statement and simultaneously,
 * the corresponding sql statement will be serve as a String stored into a specific {@link ArrayList<String[]>}
 * sql_list.<br>
 *      in such case, a "for-loop" executed will finish the work to store correct row data from website. The
 *      "for-loop" operate the String in sql_list.
 *
 * @author Hans
 * @see org.jsoup.Jsoup#connect(String)
 * @see org.jsoup.nodes.Document
 * @see org.jsoup.select.Elements
 * @see org.jsoup.select.Elements#get(int)
 * @see java.util.ArrayList
 * @see java.sql.Connection
 * @see java.sql.Statement
 */
public class WebReader implements FileReader{
    /**
     * return row data from website and insert into database.
     *
     * <ul>
     *     <li> {@code ObservableList<String[]>} webData the data list which will be return.</li>
     *     <li> {@code ArrayList<String>} sql_list is the data list store the "insert" sql statement.</li>
     *     <li> {@code String[]} webQuake is a temp variable to store single line data.<li/>
     * </ul>
     * <p> Several "for-loop" used to select the appointed element in website. And then we use these value
     * in element to generate sql statement
     * <p> Then a "for-loop" used to execute all sql statement --> finish task of inserting into database.
     *
     * @return webData contain all row data as String[].
     */
    @Override
    public ObservableList<String[]> readData() {
        ObservableList<String[]> webData = FXCollections.observableArrayList();
        ArrayList<String> sql_list = new ArrayList<>();
        try {
            int view = 1;
            for (int i = 0; i < 2; i++) {
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

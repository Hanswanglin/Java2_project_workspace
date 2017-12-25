package Controller;

import bean.quake;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Created by hans on 2017/12/21.
 */
public class WholeController {
    TableView<quake> table;
    DataController dataController;
    TableController tableController;
    MapControllerMERCATOR mapControllerMERCATOR;
    ObservableList<quake> quakes;
    int wholecounter=0;
    int source = 1;

    public WholeController(int source, String d1, String d2,String re, double mag, TableView<quake> table){
        dataController = new DataController(source);
        dataController.setDate1(d1);
        dataController.setDate2(d2);
        dataController.setRe(re);
        dataController.setMag(mag);
        tableController = new TableController(table);
        mapControllerMERCATOR = new MapControllerMERCATOR();
        dataController.refresh();
    }

    public void refresh() {
        quakes = dataController.filter();
        tableController.refresh(quakes);
        mapControllerMERCATOR.refresh(quakes);
        wholecounter=dataController.getTimes();
    }
    
    public int getCounter() {
    	return wholecounter;
    }
}

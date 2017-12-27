package Controller;

import bean.quake;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

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

    public WholeController(ChoiceBox<String> choice){

    }

    //  revoke many method of dataController to refresh
    public WholeController(int source, String d1, String d2, String re, float mag, TableView<quake> table, StackPane pane){
        dataController = new DataController(source);
        dataController.setDate1(d1);
        dataController.setDate2(d2);
        dataController.setRe(re);
        dataController.setMag(mag);
        tableController = new TableController(table);
        mapControllerMERCATOR = new MapControllerMERCATOR(pane);
    }

    // refresh the quake content
    public void refresh() {
        dataController.refresh();
        quakes = dataController.result;
        tableController.refresh(quakes);
        mapControllerMERCATOR.refresh(quakes);
        wholecounter=dataController.getTimes();
    }
    
    public int getCounter() {
    	return wholecounter;
    }
}

package Controller;

import bean.quake;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

/**
 * The {@code WholeController} class control the behavior of the entire control layer.
 * <p>The WholeController class can receive the condition from the application layer, decide others controllers' behavior.</p>
 *
 * @author  Zhang Yilin
 * @author  Pan Tianci
 * @see     WholeController
 * @see     DataController
 * @see     viewController
 * @see     application.MainUIController
 */
public class WholeController {
    DataController dataController;
    viewController tableC;
    viewController mapC;
    ObservableList<quake> quakes;

    /**
     * Creates a object to revoke methods of dataController to set condition
     *
     * @param source
     *            The option of data source.
     * @param d1
     *            The start date.
     * @param d2
     *            The end date.
     * @param re
     *            The quake region.
     * @param mag
     *            The minimum magnitude.
     * @param table
     *            The table to display data.
     * @param pane
     *            The pane to display map.
     */
    public WholeController(int source, String d1, String d2, String re, float mag, TableView<quake> table, StackPane pane){
        dataController = new DataController(source);
        dataController.setDate1(d1);
        dataController.setDate2(d2);
        dataController.setRe(re);
        dataController.setMag(mag);
        tableC = new TableController(table);
        mapC = new MapControllerMERCATOR(pane);
    }

    /**
     * refresh the quake content
     */
    public void refresh() {
        dataController.refresh();
        quakes = dataController.getResult();
        tableC.refresh(quakes);
        mapC.refresh(quakes);
    }

    /**
     * Get the count of data records
     *
     * @return The count of data records
     */
    public int getCounter() {
        return dataController.getTimes();
    }
}

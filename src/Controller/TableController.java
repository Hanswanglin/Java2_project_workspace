package Controller;

import bean.quake;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The {@code TableController} class display quakes on the table.
 *
 * @author  Pan Tianci
 * @see     WholeController
 */
public class TableController implements viewController {
    TableView<quake> table;

    /**
     * Creates a object to get table.
     *
     * @param table
     *            The table to display the data.
     */
    public TableController(TableView<quake> table) {
        this.table = table;
    }

    /**
     * Refresh the display data according to opposite data.
     *
     * @param data The data that need to display.
     */
    public void refresh(ObservableList<quake> data) {
        ObservableList<TableColumn<quake, ?>> observableList = table.getColumns();
        observableList.get(0).setCellValueFactory(new PropertyValueFactory("id"));
        observableList.get(1).setCellValueFactory(new PropertyValueFactory("longitude"));
        observableList.get(2).setCellValueFactory(new PropertyValueFactory("latitude"));
        observableList.get(3).setCellValueFactory(new PropertyValueFactory("magnitude"));
        observableList.get(4).setCellValueFactory(new PropertyValueFactory("depth"));
        observableList.get(5).setCellValueFactory(new PropertyValueFactory("region"));
        observableList.get(6).setCellValueFactory(new PropertyValueFactory("date"));

        table.setItems(data);
    }
}

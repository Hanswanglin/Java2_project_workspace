package application;

import Controller.DataController;
import Controller.WholeController;
import bean.quake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The {@code MainUIController} class control the action of MainUI.fxml.
 * <p>It defines actions of buttons</p>
 *
 * @author  Zhang Yilin
 * @author  Pan Tianci
 * @see     WholeController
 * @see     DataController
 */
public class MainUIController {
	/**
	 * Define the initialize action of the GUI.
     */
	@FXML
	protected void initialize(){
        region.setValue(DataController.wordWide);
        searchAction(null);
        choice();
    }
	@FXML
	private DatePicker d1;
	@FXML
	private DatePicker d2;
	@FXML
	private Slider mag1;
	@FXML
	private TableView<quake> table;
	@FXML
	private Label counter;
    @FXML
    private ChoiceBox<String> region;
    @FXML
    private StackPane pane;

	private int source = 1;

	/**
	 * Event Listener on region.onAction.
	 */
	@FXML
	public void choice() {
        ArrayList<String> menu = new ArrayList<String>(DataController.getRegions());
        for(int i=0;i<menu.size();i++) {
            region.getItems().add(menu.get(i).replaceAll("\"", ""));
        }
    }

	/**
	 * Event Listener on searchAction.onAction.
	 */
	public void searchAction(ActionEvent event) {
		Date now = new Date();
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		String date1 = new String("2016-01-01");
		String date2 = new String(format.format(now));
		if(d1.getValue() != null)date1 = d1.getValue().toString();
		if(d2.getValue() != null)date2 = d2.getValue().toString();
		float mag = (float) mag1.getValue();
		String re="-- World Wide --";
		if(region.getValue()!=null) {
			re = region.getValue();
		}
		// send condition data to back-end
		WholeController wholeController = new WholeController(source,date1,date2,re,mag,table,pane);
		// display data
		wholeController.refresh();
		counter.setText(wholeController.getCounter()+"  selected");
	}

    /**
     * Event Listener on source choice.
     */
	public void csv() {
		this.source = 1;
	}

	public void db() {
		this.source = 2;
	}

	public void web() {
		this.source = 3;
	}
}

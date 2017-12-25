package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import Controller.WholeController;
import bean.quake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;

public class MainUIController {
	@FXML
	private Button CSV;
	@FXML
	private Button SQL;
	@FXML
	private Button Web;
	@FXML
	private DatePicker d1;
	@FXML
	private DatePicker d2;
	@FXML
	private Slider mag1;
	@FXML
	private Button button;
	@FXML
	private TableView<quake> table;
	@FXML
	private Label counter;
	@FXML
	private ChoiceBox<String> choice;
	@FXML
	private Button load;
	
	private int source = 1;
	// Event Listener on MenuButton.onAction
	@FXML
	public void menub() {
		choice.getItems().add("-- World Wide --");
		File csv = new File("src/dataSource/earthquakes.csv");
		LinkedList<String> menu = new LinkedList<String>();// CSV??件路�?
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        try {
            br.readLine();//读�??表头
            while ((line = br.readLine()) != null)  //读�???��????容�?line????
            {
                String[] l = line.split(",");
                String sel = l[6];
                if(!menu.contains(sel)) {
                	menu.add(sel);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        for(int i=0;i<menu.size();i++) {
        	choice.getItems().add(menu.get(i).replaceAll("\"", ""));
        }
        load.setVisible(false);
	}
	// click search button
	public void evenButton(ActionEvent event) {
		Date now = new Date();
		String date1 = new String("2017-10-15");
		String date2 = new String(now.toString().substring(0, 11));
		if(d1.getValue() != null)date1 = d1.getValue().toString();
		if(d2.getValue() != null)date2 = d2.getValue().toString();
		double mag = mag1.getValue();
		String re="-- World Wide --";
		if(choice.getValue()!=null) {
			re = choice.getValue();
		}
		// send condition data to back-end
		WholeController wholeController = new WholeController(source,date1,date2,re,mag,table);
		// display data
		wholeController.refresh();
		counter.setText(wholeController.getCounter()+"  selected");
	}

	// cource file choice
	public void csv() {
		this.source = 1;
	}

	public void db() {
		this.source = 2;
	}

	public void web() {
		this.source = 3;
	}

	// ����ը��(?����㣩?? �ߩ���
	// ���ֹ�����˵������������ǰ��ʵ������ѡ��ĸ�ʽת�������Ǻ�fxml�ļ����ഫ�ݵķ�����������ͷϵ���
	// ��Ҫ������ѡ�����������ĵ�ԭ��
	// 1. "/"��"-"���滻, "MM-DD-YYYY" to "YYYY-MM-DD",����formatת���Ϳ�
	// 2. ʵ��date2��Զ��date1���棬����ѡ��date1֮ǰ������
	// ��ؽ̳̿ɲο�http://www.java2s.com/Tutorials/Java/JavaFX/0540__JavaFX_DatePicker.htm
	// ���������ҵ���Ч��Դ����po������
	//
	// ���Ŀǰ����ѡ������û�а취������Ч���ڵģ�ֻ���ִ�������Ч����
	// ��ͷʹ��_(??`�� ��)_
	
//	public void DatePickerSetting(ActionEvent date) {
//		Calendar d1 = Calendar.getInstance();
//		StringConverter converter = new StringConverter<LocalDate>() {
//			DateTimeFormatter dateFormatter =
//					DateTimeFormatter.ofPattern("YYYY-MM-DD");
//			@Override
//			public String toString(LocalDate date) {
//				if (date != null) {
//					return dateFormatter.format(date);
//				} else {
//					return "";
//				}
//			}
//			@Override
//			public LocalDate fromString(String string) {
//				if (string != null && !string.isEmpty()) {
//					return LocalDate.parse(string, dateFormatter);
//				} else {
//					return null;
//				}
//			}
//		};
//		d1.getTime();
//		date.requestFocus();
//	}
}

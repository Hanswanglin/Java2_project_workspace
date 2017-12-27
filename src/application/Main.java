package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			stage.setTitle("Earthquake Search");
			stage.setMinWidth(960);
			stage.setMinHeight(800);
			stage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource("application.css")
							.toExternalForm());
			stage.setScene(scene);
			stage.show();


        } catch(Exception e) {
			e.printStackTrace();
		}
	}
}


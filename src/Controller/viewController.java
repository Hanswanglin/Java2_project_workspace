package Controller;

import bean.quake;
import javafx.collections.ObservableList;


public interface viewController {
    void refresh(ObservableList<quake> data);
}

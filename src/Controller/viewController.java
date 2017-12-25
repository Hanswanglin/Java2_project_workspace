package Controller;

import bean.quake;
import javafx.collections.ObservableList;

/**
 * Created by hans on 2017/12/21.
 */
public interface viewController {
    void refresh(ObservableList<quake> data);
}

package Controller;

import bean.quake;
import javafx.collections.ObservableList;

/**
 * The {@code viewController} interface define the control of Graphical User Interface's show.
 * <p>It has two sub-classes, TableController and MapControllerMECTOR, each of them control a part of GUI's view.</p>
 *
 * @author  Zhang Yilin
 * @see     WholeController
 * @see     TableController
 * @see     MapControllerMERCATOR
 */
public interface viewController {
    /**
     * Refresh the display according to opposite data.
     *
     * @param data The data that need to display.
     */
    void refresh(ObservableList<quake> data);
}

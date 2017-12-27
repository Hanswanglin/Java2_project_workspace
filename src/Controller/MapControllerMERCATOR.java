package Controller;

import bean.quake;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Created by hans on 2017/12/21.
 */
public class MapControllerMERCATOR implements viewController {
    double width;
    double height;
    double meridian = 180;
    double diameter = 0;
    StackPane pane;

    public MapControllerMERCATOR(StackPane pane){
        this.pane = pane;
        this.width = pane.getWidth();
        this.height = pane.getHeight();
    }

    public void refresh(ObservableList<quake> data) {
        pane.getChildren().clear();
        double[] xy;
        // draw marks
        Canvas mercator = new Canvas(width, height);
        GraphicsContext mark = mercator.getGraphicsContext2D();
        mark.setFill(Color.color(1,0,0,0.3));
        for (quake q:data) {
            diameter = (int)(2*q.getMagnitude());
            xy = getCoord(q.getLongitude(),q.getLatitude());
            mark.fillOval(xy[0] - diameter / 2, xy[1] - diameter / 2, diameter, diameter);
        }
        pane.getChildren().add(mercator);
    }

    private double[] getCoord(double longitude, double latitude) {
        // convert the longitude
        double new_longitude;
        new_longitude = (longitude - meridian);
        if (new_longitude < -180) {
            new_longitude += 360;
        }
        if (new_longitude > 180) {
            new_longitude -= 360;
        }
        // caculate the projection, get X,Y
        double lambda = new_longitude * Math.PI / 180.0;
        double phi = latitude * Math.PI / 180.0;
        double[] coordinates = new double[2];
        coordinates[0] = (width / 2.0 / Math.PI) * lambda;
        coordinates[1] = (width / 2.0 / Math.PI) * Math.log(Math.tan(Math.PI / 4.0 + (phi / 2.0)));
        // caculate the X,Y of img
        coordinates[0] = (Math.round(coordinates[0]) + Math.round(width/2));
        coordinates[1] = (Math.round(height/2) - Math.round(coordinates[1]));
        return coordinates;
    }
}

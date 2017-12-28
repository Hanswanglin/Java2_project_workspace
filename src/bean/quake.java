package bean;

import javafx.beans.property.*;

/**
 * The {@code quake} class construct a quake object.
 * <p>Its attributes including id, date, latitude, longitude, depth, magnitude and region.</p>
 *
 * @author  Pan Tianci
 */
public class quake {
	private IntegerProperty id;
    private StringProperty  date;
    private FloatProperty   latitude;
    private FloatProperty   longitude;
    private IntegerProperty depth;
    private FloatProperty   magnitude;
    private StringProperty  region;

    /**
     * Creates a quake object.
     *
     * @param id A quake's identifier, a primary key in the database.
     * @param date Earthquake time.
     * @param latitude Earthquake latitude.
     * @param longitude Earthquake longitude.
     * @param depth Focal depth.
     * @param magnitude Earthquake magnitude.
     * @param region Earthquake region.
     */
    public quake(int id, String date,
                 float latitude, float longitude,
                 int depth, float magnitude,
                 String region) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.latitude = new SimpleFloatProperty(latitude);
        this.longitude = new SimpleFloatProperty(longitude);
        this.depth = new SimpleIntegerProperty(depth);
        this.magnitude = new SimpleFloatProperty(magnitude);
        this.region = new SimpleStringProperty(region);
    }

    /**
     * Get the quake's identifier.
     * @return The quake's identifier.
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Get the value of the quake's identifier.
     * @return The value of the quake's identifier.
     */
    public int getId() {
        return id.getValue();
    }

    /**
     * Get the quake's date.
     * @return The quake's date.
     */
    public StringProperty dateProperty() {
        return date;
    }

    /**
     * Get the value of the quake's date.
     * @return The value of the quake's date.
     */
    public String getDate() {
        return date.getValue();
    }

    /**
     * Get the quake's latitude.
     * @return The quake's latitude.
     */
    public FloatProperty latitudeProperty() {
        return latitude;
    }

    /**
     * Get the value of the quake's latitude.
     * @return The value of the quake's latitude.
     */
    public float getLatitude() {
        return latitude.getValue();
    }

    /**
     * Get the quake's longitude.
     * @return The quake's logitude.
     */
    public FloatProperty longitudeProperty() {
        return longitude;
    }

    /**
     * Get the value of the quake's longitude.
     * @return The value of the quake's longitude.
     */
    public float getLongitude() {
        return longitude.getValue();
    }

    /**
     * Get the quake's depth.
     * @return The quake's depth.
     */
    public IntegerProperty depthProperty() {
        return depth;
    }

    /**
     * Get the value of the quake's depth.
     * @return The value of the quake's depth.
     */
    public int getDepth() {
        return depth.getValue();
    }

    /**
     * Get the quake's magitude.
     * @return The quake's magitude.
     */
    public FloatProperty magnitudeProperty() {
        return magnitude;
    }

    /**
     * Get the value of the quake's magnitude.
     * @return The value of the quake's magnitude.
     */
    public float getMagnitude() {
        return magnitude.getValue();
    }

    /**
     * Get the quake's region.
     * @return The quake's region.
     */
    public StringProperty regionProperty() {
        return region;
    }

    /**
     * Get the value of the quake's region.
     * @return The value of the quake's region.
     */
    public String getRegion() {
        return region.getValue();
    }

}

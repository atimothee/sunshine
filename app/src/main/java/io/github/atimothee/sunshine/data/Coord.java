package io.github.atimothee.sunshine.data;

import com.google.gson.annotations.Expose;

/**
 * Created by Timo on 4/1/15.
 */
public class Coord {

    @Expose
    private Double lon;
    @Expose
    private Double lat;

    /**
     * @return The lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

}

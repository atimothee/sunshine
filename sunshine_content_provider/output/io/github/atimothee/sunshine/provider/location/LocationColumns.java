package io.github.atimothee.sunshine.provider.location;

import android.net.Uri;
import android.provider.BaseColumns;

import io.github.atimothee.sunshine.provider.SunshineProvider;
import io.github.atimothee.sunshine.provider.location.LocationColumns;
import io.github.atimothee.sunshine.provider.weather.WeatherColumns;

/**
 * Columns for the {@code location} table.
 */
public class LocationColumns implements BaseColumns {
    public static final String TABLE_NAME = "location";
    public static final Uri CONTENT_URI = Uri.parse(SunshineProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String LOCATION_SETTING = "location_setting";

    public static final String CITY_NAME = "city_name";

    public static final String LATITUDE = "latitude";

    public static final String LONGITUDE = "longitude";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            LOCATION_SETTING,
            CITY_NAME,
            LATITUDE,
            LONGITUDE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(LOCATION_SETTING) || c.contains("." + LOCATION_SETTING)) return true;
            if (c.equals(CITY_NAME) || c.contains("." + CITY_NAME)) return true;
            if (c.equals(LATITUDE) || c.contains("." + LATITUDE)) return true;
            if (c.equals(LONGITUDE) || c.contains("." + LONGITUDE)) return true;
        }
        return false;
    }

}

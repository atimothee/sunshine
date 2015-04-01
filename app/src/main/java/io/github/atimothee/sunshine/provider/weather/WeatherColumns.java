package io.github.atimothee.sunshine.provider.weather;

import android.net.Uri;
import android.provider.BaseColumns;

import io.github.atimothee.sunshine.provider.SunshineProvider;
import io.github.atimothee.sunshine.provider.location.LocationColumns;
import io.github.atimothee.sunshine.provider.weather.WeatherColumns;

/**
 * Columns for the {@code weather} table.
 */
public class WeatherColumns implements BaseColumns {
    public static final String TABLE_NAME = "weather";
    public static final Uri CONTENT_URI = Uri.parse(SunshineProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String DATE = "date";

    public static final String LOCATION_SETTING_ID = "location_setting_id";

    public static final String SHORT_DESC = "short_desc";

    public static final String WEATHER_ID = "weather_id";

    public static final String HUMIDITY = "humidity";

    public static final String WIND_SPEED = "wind_speed";

    public static final String MAX_TEMP = "max_temp";

    public static final String MIN_TEMP = "min_temp";

    public static final String DEGREES = "degrees";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            DATE,
            LOCATION_SETTING_ID,
            SHORT_DESC,
            WEATHER_ID,
            HUMIDITY,
            WIND_SPEED,
            MAX_TEMP,
            MIN_TEMP,
            DEGREES
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(LOCATION_SETTING_ID) || c.contains("." + LOCATION_SETTING_ID)) return true;
            if (c.equals(SHORT_DESC) || c.contains("." + SHORT_DESC)) return true;
            if (c.equals(WEATHER_ID) || c.contains("." + WEATHER_ID)) return true;
            if (c.equals(HUMIDITY) || c.contains("." + HUMIDITY)) return true;
            if (c.equals(WIND_SPEED) || c.contains("." + WIND_SPEED)) return true;
            if (c.equals(MAX_TEMP) || c.contains("." + MAX_TEMP)) return true;
            if (c.equals(MIN_TEMP) || c.contains("." + MIN_TEMP)) return true;
            if (c.equals(DEGREES) || c.contains("." + DEGREES)) return true;
        }
        return false;
    }

    public static final String PREFIX_LOCATION = TABLE_NAME + "__" + LocationColumns.TABLE_NAME;
}

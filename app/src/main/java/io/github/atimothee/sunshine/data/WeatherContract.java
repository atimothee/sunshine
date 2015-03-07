package io.github.atimothee.sunshine.data;

import android.provider.BaseColumns;

/**
 * Created by Timo on 3/7/15.
 */
public class WeatherContract {

    public static class WeatherEntry implements BaseColumns {
        public final static String TABLE_NAME = "weather";
        public final static String COLUMN_MAX_TEMP = "max_temp";
        public final static String COLUMN_MIN_TEMP = "min_temp";
        public final static String COLUMN_PRESSURE = "pressure";
        public final static String COLUMN_WIND_SPEED = "wind_speed";
        public final static String COLUMN_WEATHER_ID = "weather_id";
        public final static String COLUMN_LOC_KEY = "location";
        public static final String COLUMN_DATETEXT = "date";
        public static final String COLUMN_DEGREES = "degrees";
        public static final String COLUMN_SHORT_DESC = "short_desc";
    }

    public static class LocationEntry implements BaseColumns{
        public final static String TABLE_NAME = "location";
        public final static String COLUMN_CITY_NAME = "city_name";
        public final static String COLUMN_LOCATION_SETTING = "location_setting";
        public static final String COLUMN_COORD_LAT = "latitude";
        public static final String COLUMN_COORD_LONG = "longitude";
    }
}

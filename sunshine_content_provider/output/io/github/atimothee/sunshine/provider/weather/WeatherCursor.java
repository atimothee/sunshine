package io.github.atimothee.sunshine.provider.weather;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.github.atimothee.sunshine.provider.base.AbstractCursor;
import io.github.atimothee.sunshine.provider.location.*;

/**
 * Cursor wrapper for the {@code weather} table.
 */
public class WeatherCursor extends AbstractCursor implements WeatherModel {
    public WeatherCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(WeatherColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDate() {
        String res = getStringOrNull(WeatherColumns.DATE);
        return res;
    }

    /**
     * Get the {@code location_setting_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getLocationSettingId() {
        Integer res = getIntegerOrNull(WeatherColumns.LOCATION_SETTING_ID);
        return res;
    }

    /**
     * Get the {@code location_setting} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocationLocationSetting() {
        String res = getStringOrNull(LocationColumns.LOCATION_SETTING);
        return res;
    }

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocationCityName() {
        String res = getStringOrNull(LocationColumns.CITY_NAME);
        return res;
    }

    /**
     * Get the {@code latitude} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getLocationLatitude() {
        Float res = getFloatOrNull(LocationColumns.LATITUDE);
        return res;
    }

    /**
     * Get the {@code longitude} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getLocationLongitude() {
        Float res = getFloatOrNull(LocationColumns.LONGITUDE);
        return res;
    }

    /**
     * Get the {@code short_desc} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getShortDesc() {
        String res = getStringOrNull(WeatherColumns.SHORT_DESC);
        return res;
    }

    /**
     * Get the {@code weather_id} value.
     */
    public int getWeatherId() {
        Integer res = getIntegerOrNull(WeatherColumns.WEATHER_ID);
        if (res == null)
            throw new NullPointerException("The value of 'weather_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getHumidity() {
        Float res = getFloatOrNull(WeatherColumns.HUMIDITY);
        return res;
    }

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getWindSpeed() {
        Float res = getFloatOrNull(WeatherColumns.WIND_SPEED);
        return res;
    }

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getMaxTemp() {
        Float res = getFloatOrNull(WeatherColumns.MAX_TEMP);
        return res;
    }

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getMinTemp() {
        Float res = getFloatOrNull(WeatherColumns.MIN_TEMP);
        return res;
    }

    /**
     * Get the {@code degrees} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getDegrees() {
        Float res = getFloatOrNull(WeatherColumns.DEGREES);
        return res;
    }
}

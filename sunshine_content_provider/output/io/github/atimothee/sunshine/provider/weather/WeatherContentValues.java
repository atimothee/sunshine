package io.github.atimothee.sunshine.provider.weather;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.github.atimothee.sunshine.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code weather} table.
 */
public class WeatherContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return WeatherColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable WeatherSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public WeatherContentValues putDate(@Nullable String value) {
        mContentValues.put(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherContentValues putDateNull() {
        mContentValues.putNull(WeatherColumns.DATE);
        return this;
    }

    public WeatherContentValues putLocationSettingId(@Nullable Integer value) {
        mContentValues.put(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherContentValues putLocationSettingIdNull() {
        mContentValues.putNull(WeatherColumns.LOCATION_SETTING_ID);
        return this;
    }

    public WeatherContentValues putShortDesc(@Nullable String value) {
        mContentValues.put(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherContentValues putShortDescNull() {
        mContentValues.putNull(WeatherColumns.SHORT_DESC);
        return this;
    }

    public WeatherContentValues putWeatherId(int value) {
        mContentValues.put(WeatherColumns.WEATHER_ID, value);
        return this;
    }


    public WeatherContentValues putHumidity(@Nullable Float value) {
        mContentValues.put(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherContentValues putHumidityNull() {
        mContentValues.putNull(WeatherColumns.HUMIDITY);
        return this;
    }

    public WeatherContentValues putWindSpeed(@Nullable Float value) {
        mContentValues.put(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherContentValues putWindSpeedNull() {
        mContentValues.putNull(WeatherColumns.WIND_SPEED);
        return this;
    }

    public WeatherContentValues putMaxTemp(@Nullable Float value) {
        mContentValues.put(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherContentValues putMaxTempNull() {
        mContentValues.putNull(WeatherColumns.MAX_TEMP);
        return this;
    }

    public WeatherContentValues putMinTemp(@Nullable Float value) {
        mContentValues.put(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherContentValues putMinTempNull() {
        mContentValues.putNull(WeatherColumns.MIN_TEMP);
        return this;
    }

    public WeatherContentValues putDegrees(@Nullable Float value) {
        mContentValues.put(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherContentValues putDegreesNull() {
        mContentValues.putNull(WeatherColumns.DEGREES);
        return this;
    }
}

package io.github.atimothee.sunshine.provider.location;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.github.atimothee.sunshine.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code location} table.
 */
public class LocationCursor extends AbstractCursor implements LocationModel {
    public LocationCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(LocationColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code location_setting} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocationSetting() {
        String res = getStringOrNull(LocationColumns.LOCATION_SETTING);
        return res;
    }

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCityName() {
        String res = getStringOrNull(LocationColumns.CITY_NAME);
        return res;
    }

    /**
     * Get the {@code latitude} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getLatitude() {
        Float res = getFloatOrNull(LocationColumns.LATITUDE);
        return res;
    }

    /**
     * Get the {@code longitude} value.
     * Can be {@code null}.
     */
    @Nullable
    public Float getLongitude() {
        Float res = getFloatOrNull(LocationColumns.LONGITUDE);
        return res;
    }
}

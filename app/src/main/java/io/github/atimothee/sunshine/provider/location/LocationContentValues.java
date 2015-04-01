package io.github.atimothee.sunshine.provider.location;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.github.atimothee.sunshine.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code location} table.
 */
public class LocationContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return LocationColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable LocationSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public LocationContentValues putLocationSetting(@Nullable String value) {
        mContentValues.put(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationContentValues putLocationSettingNull() {
        mContentValues.putNull(LocationColumns.LOCATION_SETTING);
        return this;
    }

    public LocationContentValues putCityName(@Nullable String value) {
        mContentValues.put(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationContentValues putCityNameNull() {
        mContentValues.putNull(LocationColumns.CITY_NAME);
        return this;
    }

    public LocationContentValues putLatitude(@Nullable Float value) {
        mContentValues.put(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationContentValues putLatitudeNull() {
        mContentValues.putNull(LocationColumns.LATITUDE);
        return this;
    }

    public LocationContentValues putLongitude(@Nullable Float value) {
        mContentValues.put(LocationColumns.LONGITUDE, value);
        return this;
    }

    public LocationContentValues putLongitudeNull() {
        mContentValues.putNull(LocationColumns.LONGITUDE);
        return this;
    }
}

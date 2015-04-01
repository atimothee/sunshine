package io.github.atimothee.sunshine.provider.location;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import io.github.atimothee.sunshine.provider.base.AbstractSelection;

/**
 * Selection for the {@code location} table.
 */
public class LocationSelection extends AbstractSelection<LocationSelection> {
    @Override
    protected Uri baseUri() {
        return LocationColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code LocationCursor} object, which is positioned before the first entry, or null.
     */
    public LocationCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new LocationCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public LocationCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public LocationCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public LocationSelection id(long... value) {
        addEquals("location." + LocationColumns._ID, toObjectArray(value));
        return this;
    }

    public LocationSelection locationSetting(String... value) {
        addEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingNot(String... value) {
        addNotEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingLike(String... value) {
        addLike(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingContains(String... value) {
        addContains(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingStartsWith(String... value) {
        addStartsWith(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingEndsWith(String... value) {
        addEndsWith(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection cityName(String... value) {
        addEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameNot(String... value) {
        addNotEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameLike(String... value) {
        addLike(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameContains(String... value) {
        addContains(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameStartsWith(String... value) {
        addStartsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameEndsWith(String... value) {
        addEndsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection latitude(Float... value) {
        addEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationSelection latitudeNot(Float... value) {
        addNotEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationSelection latitudeGt(float value) {
        addGreaterThan(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationSelection latitudeGtEq(float value) {
        addGreaterThanOrEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationSelection latitudeLt(float value) {
        addLessThan(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationSelection latitudeLtEq(float value) {
        addLessThanOrEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public LocationSelection longitude(Float... value) {
        addEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public LocationSelection longitudeNot(Float... value) {
        addNotEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public LocationSelection longitudeGt(float value) {
        addGreaterThan(LocationColumns.LONGITUDE, value);
        return this;
    }

    public LocationSelection longitudeGtEq(float value) {
        addGreaterThanOrEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public LocationSelection longitudeLt(float value) {
        addLessThan(LocationColumns.LONGITUDE, value);
        return this;
    }

    public LocationSelection longitudeLtEq(float value) {
        addLessThanOrEquals(LocationColumns.LONGITUDE, value);
        return this;
    }
}

package io.github.atimothee.sunshine.provider.weather;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import io.github.atimothee.sunshine.provider.base.AbstractSelection;
import io.github.atimothee.sunshine.provider.location.*;

/**
 * Selection for the {@code weather} table.
 */
public class WeatherSelection extends AbstractSelection<WeatherSelection> {
    @Override
    protected Uri baseUri() {
        return WeatherColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code WeatherCursor} object, which is positioned before the first entry, or null.
     */
    public WeatherCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new WeatherCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public WeatherCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public WeatherCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public WeatherSelection id(long... value) {
        addEquals("weather." + WeatherColumns._ID, toObjectArray(value));
        return this;
    }

    public WeatherSelection date(String... value) {
        addEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateNot(String... value) {
        addNotEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateLike(String... value) {
        addLike(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateContains(String... value) {
        addContains(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateStartsWith(String... value) {
        addStartsWith(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateEndsWith(String... value) {
        addEndsWith(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection locationSettingId(Integer... value) {
        addEquals(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherSelection locationSettingIdNot(Integer... value) {
        addNotEquals(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherSelection locationSettingIdGt(int value) {
        addGreaterThan(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherSelection locationSettingIdGtEq(int value) {
        addGreaterThanOrEquals(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherSelection locationSettingIdLt(int value) {
        addLessThan(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherSelection locationSettingIdLtEq(int value) {
        addLessThanOrEquals(WeatherColumns.LOCATION_SETTING_ID, value);
        return this;
    }

    public WeatherSelection locationLocationSetting(String... value) {
        addEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingNot(String... value) {
        addNotEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingLike(String... value) {
        addLike(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingContains(String... value) {
        addContains(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingStartsWith(String... value) {
        addStartsWith(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingEndsWith(String... value) {
        addEndsWith(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationCityName(String... value) {
        addEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameNot(String... value) {
        addNotEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameLike(String... value) {
        addLike(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameContains(String... value) {
        addContains(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameStartsWith(String... value) {
        addStartsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameEndsWith(String... value) {
        addEndsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationLatitude(Float... value) {
        addEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public WeatherSelection locationLatitudeNot(Float... value) {
        addNotEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public WeatherSelection locationLatitudeGt(float value) {
        addGreaterThan(LocationColumns.LATITUDE, value);
        return this;
    }

    public WeatherSelection locationLatitudeGtEq(float value) {
        addGreaterThanOrEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public WeatherSelection locationLatitudeLt(float value) {
        addLessThan(LocationColumns.LATITUDE, value);
        return this;
    }

    public WeatherSelection locationLatitudeLtEq(float value) {
        addLessThanOrEquals(LocationColumns.LATITUDE, value);
        return this;
    }

    public WeatherSelection locationLongitude(Float... value) {
        addEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public WeatherSelection locationLongitudeNot(Float... value) {
        addNotEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public WeatherSelection locationLongitudeGt(float value) {
        addGreaterThan(LocationColumns.LONGITUDE, value);
        return this;
    }

    public WeatherSelection locationLongitudeGtEq(float value) {
        addGreaterThanOrEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public WeatherSelection locationLongitudeLt(float value) {
        addLessThan(LocationColumns.LONGITUDE, value);
        return this;
    }

    public WeatherSelection locationLongitudeLtEq(float value) {
        addLessThanOrEquals(LocationColumns.LONGITUDE, value);
        return this;
    }

    public WeatherSelection shortDesc(String... value) {
        addEquals(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescNot(String... value) {
        addNotEquals(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescLike(String... value) {
        addLike(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescContains(String... value) {
        addContains(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescStartsWith(String... value) {
        addStartsWith(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescEndsWith(String... value) {
        addEndsWith(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection weatherId(int... value) {
        addEquals(WeatherColumns.WEATHER_ID, toObjectArray(value));
        return this;
    }

    public WeatherSelection weatherIdNot(int... value) {
        addNotEquals(WeatherColumns.WEATHER_ID, toObjectArray(value));
        return this;
    }

    public WeatherSelection weatherIdGt(int value) {
        addGreaterThan(WeatherColumns.WEATHER_ID, value);
        return this;
    }

    public WeatherSelection weatherIdGtEq(int value) {
        addGreaterThanOrEquals(WeatherColumns.WEATHER_ID, value);
        return this;
    }

    public WeatherSelection weatherIdLt(int value) {
        addLessThan(WeatherColumns.WEATHER_ID, value);
        return this;
    }

    public WeatherSelection weatherIdLtEq(int value) {
        addLessThanOrEquals(WeatherColumns.WEATHER_ID, value);
        return this;
    }

    public WeatherSelection humidity(Float... value) {
        addEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityNot(Float... value) {
        addNotEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityGt(float value) {
        addGreaterThan(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityLt(float value) {
        addLessThan(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection windSpeed(Float... value) {
        addEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedNot(Float... value) {
        addNotEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedGt(float value) {
        addGreaterThan(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedLt(float value) {
        addLessThan(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection windSpeedLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.WIND_SPEED, value);
        return this;
    }

    public WeatherSelection maxTemp(Float... value) {
        addEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempNot(Float... value) {
        addNotEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempGt(float value) {
        addGreaterThan(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempLt(float value) {
        addLessThan(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection maxTempLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.MAX_TEMP, value);
        return this;
    }

    public WeatherSelection minTemp(Float... value) {
        addEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempNot(Float... value) {
        addNotEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempGt(float value) {
        addGreaterThan(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempLt(float value) {
        addLessThan(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection minTempLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.MIN_TEMP, value);
        return this;
    }

    public WeatherSelection degrees(Float... value) {
        addEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesNot(Float... value) {
        addNotEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesGt(float value) {
        addGreaterThan(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesGtEq(float value) {
        addGreaterThanOrEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesLt(float value) {
        addLessThan(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesLtEq(float value) {
        addLessThanOrEquals(WeatherColumns.DEGREES, value);
        return this;
    }
}

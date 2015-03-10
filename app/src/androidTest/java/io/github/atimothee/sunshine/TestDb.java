package io.github.atimothee.sunshine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import io.github.atimothee.sunshine.data.WeatherContract;
import io.github.atimothee.sunshine.data.WeatherDbHelper;
import io.github.atimothee.sunshine.data.WeatherContract.LocationEntry;
import io.github.atimothee.sunshine.data.WeatherContract.WeatherEntry;

/**
 * Created by Timo on 3/6/15.
 */
public class TestDb extends AndroidTestCase{
    private static final String LOG_TAG = TestDb.class.getSimpleName();
    public void testCreateDb() throws Throwable{
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb(){
        String testName = "Kampala";
        String testLocationSetting = "99705";
        double testLatitude = 64.772;
        double testLongitude = -147.355;
        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_CITY_NAME, testName);
        values.put(LocationEntry.COLUMN_LOCATION_SETTING, testLocationSetting);
        values.put(LocationEntry.COLUMN_COORD_LAT, testLatitude);
        values.put(LocationEntry.COLUMN_COORD_LONG, testLongitude);
        long locationRowId;
        locationRowId = db.insert(LocationEntry.TABLE_NAME, null, values);
        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id: "+ locationRowId);

        String[] columns = {LocationEntry._ID, LocationEntry.COLUMN_LOCATION_SETTING, LocationEntry.COLUMN_COORD_LAT,
                LocationEntry.COLUMN_COORD_LONG, LocationEntry.COLUMN_CITY_NAME};

        Cursor cursor = db.query(LocationEntry.TABLE_NAME, columns, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int locationIndex = cursor.getColumnIndex(LocationEntry.COLUMN_LOCATION_SETTING);
            String location = cursor.getString(locationIndex);
            int nameIndex = cursor.getColumnIndex(LocationEntry.COLUMN_CITY_NAME);
            String name = cursor.getString(nameIndex);
            int latIndex = cursor.getColumnIndex(LocationEntry.COLUMN_COORD_LAT);
            double latitude = cursor.getDouble(latIndex);
            int longIndex = cursor.getColumnIndex(LocationEntry.COLUMN_COORD_LONG);
            double longitude = cursor.getDouble(longIndex);
            assertEquals(testName, name);
            assertEquals(testLocationSetting, location);
            assertEquals(testLatitude, latitude);
            assertEquals(testLongitude, longitude);
        }
        else {
            fail("No values returned!");
        }
        ContentValues weatherContentValues = new ContentValues();
        weatherContentValues.put(WeatherEntry.COLUMN_LOC_KEY, locationRowId);
        String testDate = "11/03/2015";
        weatherContentValues.put(WeatherEntry.COLUMN_DATETEXT, testDate);
        Double testDegrees = 1.1;
        weatherContentValues.put(WeatherEntry.COLUMN_DEGREES, testDegrees);
        Double testHumidity = 1.2;
        weatherContentValues.put(WeatherEntry.COLUMN_HUMIDITY, testHumidity);
        Double testMaxTemp = 20.0;
        weatherContentValues.put(WeatherEntry.COLUMN_MAX_TEMP, testMaxTemp);
        Double testMinTemp = 10.0;
        weatherContentValues.put(WeatherEntry.COLUMN_MIN_TEMP, testMinTemp);
        Double testPressure = 1.3;
        weatherContentValues.put(WeatherEntry.COLUMN_PRESSURE, testPressure);
        String testShortDesc = "Asteroids";
        weatherContentValues.put(WeatherEntry.COLUMN_SHORT_DESC, testShortDesc);
        Double testWindSpeed = 1.5;
        weatherContentValues.put(WeatherEntry.COLUMN_WIND_SPEED, testWindSpeed);
        Integer testWeatherId = 243;
        weatherContentValues.put(WeatherEntry.COLUMN_WEATHER_ID, testWeatherId);

        long weatherRowId = db.insert(WeatherEntry.TABLE_NAME, null, weatherContentValues);
        assertTrue(weatherRowId!=-1);

        String[] weatherColumns = {WeatherEntry.COLUMN_HUMIDITY, WeatherEntry.COLUMN_DATETEXT, WeatherEntry.COLUMN_LOC_KEY,
        WeatherEntry.COLUMN_DEGREES, WeatherEntry.COLUMN_MAX_TEMP, WeatherEntry.COLUMN_MIN_TEMP, WeatherEntry.COLUMN_PRESSURE,
        WeatherEntry.COLUMN_SHORT_DESC, WeatherEntry.COLUMN_WEATHER_ID, WeatherEntry.COLUMN_PRESSURE, WeatherEntry.COLUMN_WIND_SPEED};

        Cursor weatherCursor = db.query(WeatherEntry.TABLE_NAME, weatherColumns, null, null, null, null, null);
        if(weatherCursor.moveToFirst()){
            int maxIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_MAX_TEMP);
            Double maxTemp = weatherCursor.getDouble(maxIndex);
            int minIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_MIN_TEMP);
            int pressureIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_PRESSURE);
            int windIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_WIND_SPEED);
            int weatherIdIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_WEATHER_ID);
            int dateIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_DATETEXT);
            int degreesIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_DEGREES);
            int shortDescIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_SHORT_DESC);
            int humidityIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_HUMIDITY);
            int locIndex = weatherCursor.getColumnIndex(WeatherEntry.COLUMN_LOC_KEY);
            assertEquals(maxTemp, testMaxTemp);
            Double minTemp = weatherCursor.getDouble(minIndex);
            assertEquals(minTemp, testMinTemp);
            Double pressure = weatherCursor.getDouble(pressureIndex);
            assertEquals(pressure, testPressure);
            Double windSpeed = weatherCursor.getDouble(windIndex);
            assertEquals(windSpeed, testWindSpeed);
            Double humidity = weatherCursor.getDouble(humidityIndex);
            assertEquals(humidity, testHumidity);
            Double degrees = weatherCursor.getDouble(degreesIndex);
            assertEquals(degrees, testDegrees);
            String date= weatherCursor.getString(dateIndex);
            assertEquals(date, testDate);
            Integer weatherId = weatherCursor.getInt(weatherIdIndex);
            assertEquals(weatherId, testWeatherId);
            long locationId = weatherCursor.getLong(locIndex);
            assertEquals(locationId, locationRowId);

        }
        else {
            fail("No weather values returned!");
        }

    }
}

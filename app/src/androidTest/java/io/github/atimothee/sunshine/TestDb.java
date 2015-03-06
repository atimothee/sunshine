package io.github.atimothee.sunshine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.Map;
import java.util.Set;

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

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long locationRowId;
        locationRowId = db.insert(LocationEntry.TABLE_NAME, null, getLocationContentValues());
        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id: "+ locationRowId);
        Cursor cursor = db.query(LocationEntry.TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            validateCursor(getLocationContentValues(), cursor);
        }
        else {
            fail("No values returned!");
        }



        long weatherRowId = db.insert(WeatherEntry.TABLE_NAME, null, getWeatherContentValues(locationRowId));
        assertTrue(weatherRowId!=-1);

        Cursor weatherCursor = db.query(WeatherEntry.TABLE_NAME, null, null, null, null, null, null);
        if(weatherCursor.moveToFirst()){
            validateCursor(getWeatherContentValues(locationRowId), weatherCursor);

        }
        else {
            fail("No weather values returned!");
        }

    }

    static public void validateCursor(ContentValues expectedValues, Cursor valueCursor){
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for(Map.Entry<String, Object> entry: valueSet){
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse(-1==idx);
            Log.d(LOG_TAG, "column name: "+columnName);
            String expectedValue = entry.getValue().toString();
            assertEquals(expectedValue, valueCursor.getString(idx));
        }
    }

    ContentValues getWeatherContentValues(long locationId){
        ContentValues weatherContentValues = new ContentValues();
        weatherContentValues.put(WeatherEntry.COLUMN_LOC_KEY, locationId);
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
        return weatherContentValues;
    }

    ContentValues getLocationContentValues() {
        String testName = "Kampala";
        String testLocationSetting = "99705";
        double testLatitude = 64.772;
        double testLongitude = -147.355;
        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_CITY_NAME, testName);
        values.put(LocationEntry.COLUMN_LOCATION_SETTING, testLocationSetting);
        values.put(LocationEntry.COLUMN_COORD_LAT, testLatitude);
        values.put(LocationEntry.COLUMN_COORD_LONG, testLongitude);
        return values;
    }
}

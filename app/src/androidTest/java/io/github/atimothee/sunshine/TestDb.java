package io.github.atimothee.sunshine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import io.github.atimothee.sunshine.data.WeatherContract.WeatherEntry;
import io.github.atimothee.sunshine.data.WeatherDbHelper;
import io.github.atimothee.sunshine.data.WeatherContract.LocationEntry;

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
        String[] columns = {LocationEntry.COLUMN_CITY_NAME, LocationEntry.COLUMN_COORD_LONG,
        LocationEntry.COLUMN_COORD_LAT, LocationEntry.COLUMN_LOCATION_SETTING};
        Cursor cursor = db.query(LocationEntry.TABLE_NAME, columns, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int latIndex = cursor.getColumnIndex(LocationEntry.COLUMN_COORD_LAT);
            assertEquals(testLatitude, cursor.getDouble(latIndex));
            int longIndex = cursor.getColumnIndex(LocationEntry.COLUMN_COORD_LONG);
            assertEquals(testLongitude, cursor.getDouble(longIndex));
        }

        ContentValues weatherContentValues = new ContentValues();
        int testWeatherId = 345;
        weatherContentValues.put(WeatherEntry.COLUMN_WEATHER_ID, testWeatherId);
        Double testPressure = 3.5;
        weatherContentValues.put(WeatherEntry.COLUMN_PRESSURE, testPressure);
        Double testMaxTemp = 40.0;
        weatherContentValues.put(WeatherEntry.COLUMN_MAX_TEMP, testMaxTemp);
        Double testMinTemp = 14.0;
        weatherContentValues.put(WeatherEntry.COLUMN_MIN_TEMP, testMinTemp);
        weatherContentValues.put(WeatherEntry.COLUMN_LOC_KEY, locationRowId);
        String testDesc = "Cloudy";
        weatherContentValues.put(WeatherEntry.COLUMN_SHORT_DESC, testDesc);
        String testDate = "07/03/2015";
        weatherContentValues.put(WeatherEntry.COLUMN_DATETEXT, testDate);
        Double testHumidity =4.5;
        weatherContentValues.put(WeatherEntry.COLUMN_HUMIDITY, testHumidity);
        Double testDegrees =30.0;
        weatherContentValues.put(WeatherEntry.COLUMN_DEGREES, testDegrees);
        Double windSpeed = 400.0;
        weatherContentValues.put(WeatherEntry.COLUMN_WIND_SPEED, windSpeed);

        long weatherRowId = db.insert(WeatherEntry.TABLE_NAME, null, weatherContentValues);
        assertTrue(weatherRowId!=-1);
        Cursor cursor1 = db.query(WeatherEntry.TABLE_NAME, null, null, null, null, null, null);
        if(cursor1.moveToFirst()){
            int humidityIndex = cursor1.getColumnIndex(WeatherEntry.COLUMN_HUMIDITY);
            assertEquals(testHumidity, cursor1.getDouble(humidityIndex));
            int locationIndex  =cursor1.getColumnIndex(WeatherEntry.COLUMN_LOC_KEY);
            assertEquals(locationRowId, cursor1.getLong(locationIndex));
        }


    }
}

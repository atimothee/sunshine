package io.github.atimothee.sunshine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import io.github.atimothee.sunshine.data.WeatherContract;
import io.github.atimothee.sunshine.data.WeatherDbHelper;

/**
 * Created by Timo on 3/12/15.
 */
public class TestProvider extends AndroidTestCase {
    private static final String LOG_TAG = TestDb.class.getSimpleName();
    public void testDeleteDb() throws Throwable{
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
    }

    static public String TEST_CITY_NAME = "Kampala";
    static public String TEST_LOCATION = "99705";
    static public String TEST_DATE = "20150327";

    public void testInsertReadProvider(){
        double testLatitude = 64.772;
        double testLongitude = -147.355;
        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeatherContract.LocationEntry.COLUMN_CITY_NAME, TEST_CITY_NAME);
        values.put(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, TEST_LOCATION);
        values.put(WeatherContract.LocationEntry.COLUMN_COORD_LAT, testLatitude);
        values.put(WeatherContract.LocationEntry.COLUMN_COORD_LONG, testLongitude);
        long locationRowId;
        locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, values);
        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id: " + locationRowId);

        String[] columns = {WeatherContract.LocationEntry._ID, WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, WeatherContract.LocationEntry.COLUMN_COORD_LAT,
                WeatherContract.LocationEntry.COLUMN_COORD_LONG, WeatherContract.LocationEntry.COLUMN_CITY_NAME};

        Cursor cursor = mContext.getContentResolver().query(WeatherContract.LocationEntry.CONTENT_URI, columns, null, null, null);
        if(cursor.moveToFirst()){
            int locationIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);
            String location = cursor.getString(locationIndex);
            int nameIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_CITY_NAME);
            String name = cursor.getString(nameIndex);
            int latIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_COORD_LAT);
            double latitude = cursor.getDouble(latIndex);
            int longIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_COORD_LONG);
            double longitude = cursor.getDouble(longIndex);
            assertEquals(TEST_CITY_NAME, name);
            assertEquals(TEST_LOCATION, location);
            assertEquals(testLatitude, latitude);
            assertEquals(testLongitude, longitude);
        }
        else {
            fail("No values returned!");
        }
        ContentValues weatherContentValues = new ContentValues();
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_LOC_KEY, locationRowId);
        String testDate = "11/03/2015";
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_DATETEXT, testDate);
        Double testDegrees = 1.1;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, testDegrees);
        Double testHumidity = 1.2;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, testHumidity);
        Double testMaxTemp = 20.0;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, testMaxTemp);
        Double testMinTemp = 10.0;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, testMinTemp);
        Double testPressure = 1.3;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, testPressure);
        String testShortDesc = "Asteroids";
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, testShortDesc);
        Double testWindSpeed = 1.5;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, testWindSpeed);
        Integer testWeatherId = 243;
        weatherContentValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, testWeatherId);

        long weatherRowId = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, weatherContentValues);
        assertTrue(weatherRowId!=-1);

        String[] weatherColumns = {WeatherContract.WeatherEntry.COLUMN_HUMIDITY, WeatherContract.WeatherEntry.COLUMN_DATETEXT, WeatherContract.WeatherEntry.COLUMN_LOC_KEY,
                WeatherContract.WeatherEntry.COLUMN_DEGREES, WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, WeatherContract.WeatherEntry.COLUMN_PRESSURE,
                WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, WeatherContract.WeatherEntry.COLUMN_PRESSURE, WeatherContract.WeatherEntry.COLUMN_WIND_SPEED};

        Cursor weatherCursor = mContext.getContentResolver().query(WeatherContract.WeatherEntry.CONTENT_URI,
                weatherColumns, null, null, null);


        if(weatherCursor.moveToFirst()){
            int maxIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
            Double maxTemp = weatherCursor.getDouble(maxIndex);
            int minIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
            int pressureIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
            int windIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);
            int weatherIdIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
            int dateIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATETEXT);
            int degreesIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DEGREES);
            int shortDescIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
            int humidityIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
            int locIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
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
        weatherCursor.close();

        weatherCursor = mContext.getContentResolver().query(WeatherContract.WeatherEntry.buildWeatherLocation(TEST_LOCATION),
                null, null, null, null);

        if(weatherCursor.moveToFirst()){
            int maxIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
            Double maxTemp = weatherCursor.getDouble(maxIndex);
            int minIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
            int pressureIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
            int windIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);
            int weatherIdIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
            int dateIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATETEXT);
            int degreesIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DEGREES);
            int shortDescIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
            int humidityIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
            int locIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
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
        weatherCursor.close();

        weatherCursor = mContext.getContentResolver().query(WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(TEST_LOCATION, TEST_DATE),
                null, null, null, null);
        if(weatherCursor.moveToFirst()){
            int maxIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
            Double maxTemp = weatherCursor.getDouble(maxIndex);
            int minIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
            int pressureIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
            int windIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);
            int weatherIdIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
            int dateIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATETEXT);
            int degreesIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DEGREES);
            int shortDescIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
            int humidityIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
            int locIndex = weatherCursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
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

    public void testGetType(){
        String type = mContext.getContentResolver().getType(WeatherContract.WeatherEntry.CONTENT_URI);
        assertEquals(WeatherContract.WeatherEntry.CONTENT_TYPE, type);

        String testLocation = "Kampala";
        type = mContext.getContentResolver().getType(WeatherContract.WeatherEntry.buildWeatherLocation(testLocation));
        assertEquals(WeatherContract.WeatherEntry.CONTENT_TYPE, type);

        String testDate = "20150327";
        type = mContext.getContentResolver().getType(WeatherContract.WeatherEntry.buildWeatherLocationWithDate(testLocation, testDate));
        assertEquals(WeatherContract.WeatherEntry.CONTENT_ITEM_TYPE, type);

        type = mContext.getContentResolver().getType(WeatherContract.LocationEntry.CONTENT_URI);
        assertEquals(WeatherContract.LocationEntry.CONTENT_TYPE, type);

        type = mContext.getContentResolver().getType(WeatherContract.LocationEntry.buildLocationUri(1));
        assertEquals(WeatherContract.LocationEntry.CONTENT_ITEM_TYPE, type);
    }
}


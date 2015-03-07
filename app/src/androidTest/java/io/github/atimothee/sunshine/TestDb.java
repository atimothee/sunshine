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

    public void testCreateDb () throws Throwable{
        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        assertTrue(db.isOpen());
        db.close();
    }

    public void testInsertReadDb() throws Throwable{
        ContentValues contentValues = new ContentValues();
        String cityName = "Kampala";
        contentValues.put(LocationEntry._ID, 1);
        contentValues.put(LocationEntry.COLUMN_CITY_NAME, cityName);
        Double longitude = 2.4;
        contentValues.put(LocationEntry.COLUMN_COORD_LONG, longitude);
        Double latitude = 2.7;
        contentValues.put(LocationEntry.COLUMN_COORD_LAT, latitude);
        String location = "424";
        contentValues.put(LocationEntry.COLUMN_LOCATION_SETTING, location);
        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            long locationRowId = db.insert(LocationEntry.TABLE_NAME, null, contentValues);
            assertTrue(locationRowId != -1);
        }catch (Exception e){
            e.printStackTrace();
            Log.d(LOG_TAG, e.getMessage());
        }

    }


}

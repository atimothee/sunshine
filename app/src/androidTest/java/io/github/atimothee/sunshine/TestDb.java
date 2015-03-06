package io.github.atimothee.sunshine;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import io.github.atimothee.sunshine.data.WeatherDbHelper;

/**
 * Created by Timo on 3/6/15.
 */
public class TestDb extends AndroidTestCase{
    public void testCreateDb() throws Throwable{
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }
}

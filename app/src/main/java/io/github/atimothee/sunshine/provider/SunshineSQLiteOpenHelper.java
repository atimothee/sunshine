package io.github.atimothee.sunshine.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import io.github.atimothee.sunshine.BuildConfig;
import io.github.atimothee.sunshine.provider.location.LocationColumns;
import io.github.atimothee.sunshine.provider.weather.WeatherColumns;

public class SunshineSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = SunshineSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "sunshine.db";
    private static final int DATABASE_VERSION = 1;
    private static SunshineSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final SunshineSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_LOCATION = "CREATE TABLE IF NOT EXISTS "
            + LocationColumns.TABLE_NAME + " ( "
            + LocationColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LocationColumns.LOCATION_SETTING + " TEXT, "
            + LocationColumns.CITY_NAME + " TEXT, "
            + LocationColumns.LATITUDE + " REAL, "
            + LocationColumns.LONGITUDE + " REAL "
            + ", CONSTRAINT unique_name UNIQUE (location_setting) ON CONFLICT IGNORE"
            + " );";

    public static final String SQL_CREATE_TABLE_WEATHER = "CREATE TABLE IF NOT EXISTS "
            + WeatherColumns.TABLE_NAME + " ( "
            + WeatherColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WeatherColumns.DATE + " TEXT, "
            + WeatherColumns.LOCATION_SETTING_ID + " INTEGER, "
            + WeatherColumns.SHORT_DESC + " TEXT, "
            + WeatherColumns.WEATHER_ID + " INTEGER NOT NULL, "
            + WeatherColumns.HUMIDITY + " REAL, "
            + WeatherColumns.WIND_SPEED + " REAL, "
            + WeatherColumns.MAX_TEMP + " REAL, "
            + WeatherColumns.MIN_TEMP + " REAL, "
            + WeatherColumns.DEGREES + " REAL "
            + ", CONSTRAINT fk_location_setting_id FOREIGN KEY (" + WeatherColumns.LOCATION_SETTING_ID + ") REFERENCES location (_id) ON DELETE CASCADE"
            + " );";

    // @formatter:on

    public static SunshineSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static SunshineSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static SunshineSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new SunshineSQLiteOpenHelper(context);
    }

    private SunshineSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new SunshineSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static SunshineSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new SunshineSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private SunshineSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new SunshineSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_LOCATION);
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}

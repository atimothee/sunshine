package io.github.atimothee.sunshine.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Timo on 3/10/15.
 */
public class WeatherProvider extends ContentProvider{
    private static final int WEATHER = 100;
    private static final int WEATHER_WITH_LOCATION = 101;
    private static final int WEATHER_WITH_LOCATION_AND_DATE = 102;
    private static final int LOCATION = 300;
    private static final int LOCATION_ID = 301;

    private WeatherDbHelper mOpenHelper;
    private static final SQLiteQueryBuilder sWeatherLocationSettingQueryBuilder;
    static {
        sWeatherLocationSettingQueryBuilder = new SQLiteQueryBuilder();
        sWeatherLocationSettingQueryBuilder.setTables(WeatherContract.WeatherEntry.TABLE_NAME + " INNER JOIN "+
                WeatherContract.LocationEntry.TABLE_NAME+
        " ON "+ WeatherContract.WeatherEntry.TABLE_NAME+
        "."+ WeatherContract.WeatherEntry.COLUMN_LOC_KEY+
        "="+ WeatherContract.LocationEntry.TABLE_NAME+
        "."+ WeatherContract.LocationEntry._ID)
        ;
    }

    private static String sLocationSettingSelection = WeatherContract.LocationEntry.TABLE_NAME+
            "."+ WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + "= ?";

    private static String sLocationSettingWithStartDateSelection = WeatherContract.LocationEntry.TABLE_NAME+
            "."+ WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + "= ? "+
            "AND "+ WeatherContract.WeatherEntry.COLUMN_DATETEXT + ">= ?";

    private static String sLocationSettingWithDaySelection = WeatherContract.LocationEntry.TABLE_NAME+
            "."+ WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING + "= ? "+
            "AND "+ WeatherContract.WeatherEntry.COLUMN_DATETEXT + ">= ?";

    private Cursor getWeatherByLocationSetting(Uri uri, String[] projection, String sortOrder){
           String locationSetting = WeatherContract.WeatherEntry.getLocationSettingFromUri(uri);
        String startDate = WeatherContract.WeatherEntry.getStartDateFromUri(uri);
        String[] selectionArgs;
        String selection;
        if(startDate == null){
            selection = sLocationSettingSelection;
            selectionArgs = new String[]{locationSetting};

        } else{
            selection = sLocationSettingWithStartDateSelection;
            selectionArgs = new String[]{locationSetting, startDate};

        }
        return sWeatherLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(), projection
        , selection, selectionArgs, null, null, sortOrder);

    }

    private Cursor getWeatherByLocationWithDate(Uri uri, String[] projection, String sortOrder){
        String locationSetting = WeatherContract.WeatherEntry.getLocationSettingFromUri(uri);
        String day = WeatherContract.WeatherEntry.getDateFromUri(uri);
        String[] selectionArgs = new String[]{locationSetting, day};
        return sWeatherLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(), projection
                , sLocationSettingWithDaySelection, selectionArgs, null, null, sortOrder);

    }

    private static UriMatcher buildUriMatcher(){

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = WeatherContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
        matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
        matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/*", WEATHER_WITH_LOCATION_AND_DATE);

        matcher.addURI(authority, WeatherContract.PATH_LOCATION , LOCATION);
        matcher.addURI(authority, WeatherContract.PATH_LOCATION + "/#" , LOCATION_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new WeatherDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (buildUriMatcher().match(uri)){
            case WEATHER_WITH_LOCATION_AND_DATE:
                retCursor = getWeatherByLocationWithDate(uri, projection, sortOrder);
                break;
            case WEATHER_WITH_LOCATION:
                retCursor = getWeatherByLocationSetting(uri, projection, sortOrder);
                break;
            case WEATHER:
                retCursor = mOpenHelper.getReadableDatabase().query(WeatherContract.WeatherEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            case LOCATION:
                retCursor = mOpenHelper.getReadableDatabase().query(WeatherContract.LocationEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            case LOCATION_ID:
                retCursor = mOpenHelper.getReadableDatabase().query(WeatherContract.LocationEntry.TABLE_NAME,
                        projection,
                        WeatherContract.LocationEntry._ID + " = '"+ ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder);
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri "+uri);
        }
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = buildUriMatcher().match(uri);
        switch (match){
            case WEATHER_WITH_LOCATION_AND_DATE:
                return WeatherContract.WeatherEntry.CONTENT_ITEM_TYPE;
            case WEATHER_WITH_LOCATION:
                return WeatherContract.WeatherEntry.CONTENT_TYPE;
            case WEATHER:
                return WeatherContract.WeatherEntry.CONTENT_TYPE;
            case LOCATION:
                return WeatherContract.LocationEntry.CONTENT_TYPE;
            case LOCATION_ID:
                return WeatherContract.LocationEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri "+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

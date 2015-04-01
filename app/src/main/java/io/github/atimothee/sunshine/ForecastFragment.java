package io.github.atimothee.sunshine;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.github.atimothee.sunshine.data.Model;
import io.github.atimothee.sunshine.fetch.WebService;
import io.github.atimothee.sunshine.provider.location.LocationColumns;
import io.github.atimothee.sunshine.provider.location.LocationContentValues;
import io.github.atimothee.sunshine.provider.weather.WeatherColumns;
import retrofit.RestAdapter;

/**
 * Created by Timo on 2/20/15.
 */
public class ForecastFragment extends Fragment implements LoaderManager.LoaderCallbacks{
    private static String LOG_TAG = ForecastFragment.class.getSimpleName();
    private SimpleCursorAdapter mForecastAdapter;
    int[] VIEW_IDS = {R.id.description, R.id.max_temp, R.id.min_temp};
    String[] COLUMNS = {WeatherColumns.SHORT_DESC, WeatherColumns.MAX_TEMP, WeatherColumns.MIN_TEMP};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mForecastAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_forecast,null, COLUMNS, VIEW_IDS);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    public void updateWeather(){
        FetchWeatherTask weatherTask = new FetchWeatherTask(getActivity());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        weatherTask.execute(location);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] forecastArray = {
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/40",
                "Weds - Cloudy - 72/63",
                "Thurs - Asteroids - 75/65",
                "Fri - Heavy Rain - 65/56",
                "Sat - HELP TRAPPED IN WEATHER STATION - 60/51",
                "Sun - Sunny - 80/68",
        };
//        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));
//        mForecastAdapter = new ArrayAdapter<String>(getActivity(),
//                R.layout.list_item_forecast,
//                R.id.list_item_forecast_textview,
//                weekForecast);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_forecast);
        listView.setAdapter(mForecastAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String forecast = mForecastAdapter.getItem(position);
//                Intent intent = new Intent(getActivity(), DetailActivity.class)
//                        .putExtra(Intent.EXTRA_TEXT, forecast);
//                startActivity(intent);
//            }
//        });
        return rootView;
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), WeatherColumns.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        mForecastAdapter.swapCursor((Cursor) data);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    class FetchWeatherTask extends AsyncTask<String, Void, String[]> {
        private Context mContext;

        public FetchWeatherTask(Context mContext){
            this.mContext = mContext;
        }

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }
            // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

// Will contain the raw JSON response as a string.
            String forecastJsonStr = null;
            String format = "json";
            String units = "metric";
            int numDays = 7;
            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_CITY = "city";
            final String OWM_CITY_NAME = "name";
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAX = "max";
            final String OWM_MIN = "min";
            final String OWM_DATETIME = "dt";
            final String OWM_DESCRIPTION = "main";

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org")
                    .build();

            Model response = null;
            try { WebService.WeatherService service = restAdapter.create(WebService.WeatherService.class);


                response = service.fetchWeather("Kampala", format, numDays, units);
                Log.d(LOG_TAG, "retrofit "+response.getCity().getCountry());
            }catch (Exception e){
                e.printStackTrace();
            }


            LocationContentValues locationContentValues = new LocationContentValues();
            //locationContentValues.put(response.getCity().getName());
            locationContentValues.putCityName(response.getCity().getName());
            locationContentValues.putLatitude(response.getCity().getCoord().getLat().floatValue());
            locationContentValues.putLongitude(response.getCity().getCoord().getLon().floatValue());
            locationContentValues.putLocationSetting("9401");

            locationContentValues.insert(mContext.getContentResolver());

            ContentValues contentValues = new ContentValues();
            List<ContentValues> contentValuesList = new ArrayList<>();

            for(io.github.atimothee.sunshine.data.List weather: response.getList()){
                contentValues = new ContentValues();
                contentValues.put(WeatherColumns.HUMIDITY, weather.getHumidity());
                contentValues.put(WeatherColumns.WIND_SPEED, weather.getSpeed());
                contentValues.put(WeatherColumns.DEGREES, weather.getDeg());
                contentValues.put(WeatherColumns.MAX_TEMP, weather.getTemp().getMax());
                contentValues.put(WeatherColumns.MIN_TEMP, weather.getTemp().getMin());
                contentValues.put(WeatherColumns.SHORT_DESC, weather.getWeather().get(0).getDescription());
                contentValues.put(WeatherColumns.WEATHER_ID, weather.getWeather().get(0).getId());
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                contentValues.put(WeatherColumns.DATE, sf.format(new Date(weather.getDt())));
                contentValues.put(WeatherColumns.LOCATION_SETTING_ID, 1);
                contentValuesList.add(contentValues);

            }
            mContext.getContentResolver().bulkInsert(WeatherColumns.CONTENT_URI, contentValuesList.toArray(new ContentValues[contentValuesList.size()]));



            String[] dummy = {"a", "b"};
            return dummy;
        }

        @Override
        protected void onPostExecute(String[] result) {
//            if(result != null){
//                mForecastAdapter.clear();
//                for(String dayForecastStr: result) {
//                    mForecastAdapter.add(dayForecastStr);
//                }
//            }
        }
    }
}

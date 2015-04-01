package io.github.atimothee.sunshine.fetch;

import io.github.atimothee.sunshine.data.Model;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Timo on 4/1/15.
 */
public class WebService {
    public interface WeatherService {
        @GET("/data/2.5/forecast/daily")
        Model fetchWeather(@Query("q") String q, @Query("mode") String mode,
                        @Query("cnt") Integer count, @Query("units") String units);
    }
}

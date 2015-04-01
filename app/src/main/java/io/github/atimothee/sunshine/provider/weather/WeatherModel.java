package io.github.atimothee.sunshine.provider.weather;

import io.github.atimothee.sunshine.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code weather} table.
 */
public interface WeatherModel extends BaseModel {

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDate();

    /**
     * Get the {@code location_setting_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getLocationSettingId();

    /**
     * Get the {@code short_desc} value.
     * Can be {@code null}.
     */
    @Nullable
    String getShortDesc();

    /**
     * Get the {@code weather_id} value.
     */
    int getWeatherId();

    /**
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getHumidity();

    /**
     * Get the {@code wind_speed} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getWindSpeed();

    /**
     * Get the {@code max_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getMaxTemp();

    /**
     * Get the {@code min_temp} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getMinTemp();

    /**
     * Get the {@code degrees} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getDegrees();
}

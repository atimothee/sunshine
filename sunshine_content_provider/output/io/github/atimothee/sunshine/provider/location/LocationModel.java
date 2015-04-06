package io.github.atimothee.sunshine.provider.location;

import io.github.atimothee.sunshine.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code location} table.
 */
public interface LocationModel extends BaseModel {

    /**
     * Get the {@code location_setting} value.
     * Can be {@code null}.
     */
    @Nullable
    String getLocationSetting();

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCityName();

    /**
     * Get the {@code latitude} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getLatitude();

    /**
     * Get the {@code longitude} value.
     * Can be {@code null}.
     */
    @Nullable
    Float getLongitude();
}

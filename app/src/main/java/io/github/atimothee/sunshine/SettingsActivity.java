package io.github.atimothee.sunshine;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by Timo on 2/27/15.
 */
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pre_general);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}

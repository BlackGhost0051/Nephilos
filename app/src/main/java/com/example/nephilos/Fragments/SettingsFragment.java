package com.example.nephilos.Fragments;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import com.example.nephilos.R;

public class SettingsFragment  extends PreferenceFragmentCompat{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}

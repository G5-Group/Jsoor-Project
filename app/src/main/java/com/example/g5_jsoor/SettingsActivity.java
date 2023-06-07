package com.example.g5_jsoor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String languageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.setting);
        // Set the toolbar as the app bar for the activity
        setSupportActionBar(toolbar);
        // Enable the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set the navigation click listener using lambda expression
        toolbar.setNavigationOnClickListener(v -> onBackPressed());



        // Get the shared preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Set the language code to the current language preference
        languageCode = sharedPreferences.getString("language_preference", "");

        // Set the language for the app
        setLocale(languageCode);

        // Load the settings fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.settings, new SettingsFragment()).commit();
    }

    // Set the language for the app
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    // Update the language preference and restart the activity
    // Update the language preference and restart the activity
    private void updateLanguagePreference(String languageCode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language_preference", languageCode);
        editor.apply();

        // Restart the activity
        Intent intent = new Intent(this, PhoneAppPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    // Settings fragment
    public static class SettingsFragment extends PreferenceFragmentCompat {

        private ListPreference languagePreference;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Get the language preference
            languagePreference = findPreference("language_preference");

            // Set the summary to the current language preference
            languagePreference.setSummary(languagePreference.getEntry());

            // Set the listener for changes to the language preference
            languagePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    // Set the new language code
                    String languageCode = (String) newValue;

                    // Update the language preference
                    SettingsActivity activity = (SettingsActivity) getActivity();
                    activity.updateLanguagePreference(languageCode);

                    // Update the summary for the language preference
                    languagePreference.setSummary(languagePreference.getEntries()[languagePreference.findIndexOfValue(languageCode)]);

                    // Set the language for the app
                    activity.setLocale(languageCode);

                    // Return true to update the preference value
                    return true;
                }
            });
        }
    }



    }





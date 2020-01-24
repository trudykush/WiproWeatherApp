package com.weathermap.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.weathermap.R;
import com.weathermap.global.Constants;

public class Settings extends AppCompatActivity {

    ToggleButton unitSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.super.onBackPressed();
            }
        });

        unitSwitch = findViewById(R.id.unitTB);

        SharedPreferences prefs = getSharedPreferences(
                "unit", Context.MODE_PRIVATE);
        boolean isButtonSelected = prefs.getBoolean("BUTTON_SELECTED", false);

        unitSwitch.setChecked(isButtonSelected);

        unitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Constants.metricSystem = isChecked;

                if (isChecked) {
                    SharedPreferences prefs = getSharedPreferences(
                            "unit", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("BUTTON_SELECTED", true);
                    editor.commit();
                } else {
                    SharedPreferences prefs = getSharedPreferences(
                            "unit", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("BUTTON_SELECTED", false);
                    editor.commit();
                }
            }
        });
    }

}

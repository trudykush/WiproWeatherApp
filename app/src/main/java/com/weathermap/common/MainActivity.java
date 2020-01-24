package com.weathermap.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.weathermap.R;
import com.weathermap.adapters.WeatherLayoutAdapter;
import com.weathermap.global.Constants;
import com.weathermap.helperClasses.CityModel;
import com.weathermap.helperClasses.CombinedHelperModel;
import com.weathermap.helperClasses.Main;
import com.weathermap.helperClasses.Weather;
import com.weathermap.helperClasses.WeatherAPIModel;
import com.weathermap.helperClasses.Wind;
import com.weathermap.retrofitAPI.WeatherAPIs;
import com.weathermap.retrofitAPI.WeatherServiceBuilder;
import com.weathermap.settings.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    AHBottomNavigation bottomNavigation;
    Button currentLocBtn, fiveDayForecastBtn;
    RecyclerView weatherRV;

    String latitude, longitude, count;
    String appid, city, unit;

    private List<String> mWeatherList;
    private List<String> mTempList;
    private List<String> mHumidityList;
    private List<String> mWindSpeedList;

    private String mWeatherStatus;
    private String mTemp;
    private String mHumidity;
    private String mWindSpeed;

    List<String> allHeading;
    List<List<String>> allValues;

    WeatherLayoutAdapter weatherLayoutAdapter;
    private String mDate;
    List<String> mDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentLocBtn = findViewById(R.id.currentLocBtn);
        weatherRV = findViewById(R.id.weatherRV);
        fiveDayForecastBtn = findViewById(R.id.fiveDayForecastBtn);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem Main =
                new AHBottomNavigationItem(R.string.main, R.drawable.baseline_blur_on_black_18dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem Wind =
                new AHBottomNavigationItem(R.string.wind, R.drawable.baseline_ac_unit_black_18dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem Rain =
                new AHBottomNavigationItem(R.string.rain, R.drawable.baseline_cloud_black_18dp, R.color.colorPrimaryDark);

        // Add Items
        bottomNavigation.addItem(Main);
        bottomNavigation.addItem(Wind);
        bottomNavigation.addItem(Rain);
        // bottomNavigation.addItem(accountInfoItem);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Changing the colors of active and inactive items
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        //Scrolling Effect
        bottomNavigation.setBehaviorTranslationEnabled(true);

        initializingAPIValues();
        currentLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherAsPerCurrentLocationAndUnit();
            }
        });

        weatherStatusAPI();

        fiveDayForecastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializingHeadingForRecyclerView();

                assigningValuesForRecyclerView();

                settingAdapterForRecyclerView();
            }
        });
    }

    private void settingAdapterForRecyclerView() {

        weatherRV = findViewById(R.id.weatherRV);
        weatherRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        weatherLayoutAdapter = new WeatherLayoutAdapter(MainActivity.this,
                allHeading, allValues, mDateList);

        weatherRV.setItemViewCacheSize(100);
        weatherRV.setAdapter(weatherLayoutAdapter);

    }

    private void assigningValuesForRecyclerView() {

        allValues = new ArrayList<List<String>>();

        allValues.add(mWeatherList);
        allValues.add(mTempList);
        allValues.add(mHumidityList);
        allValues.add(mWindSpeedList);

    }

    private void initializingHeadingForRecyclerView() {
        allHeading = new ArrayList<>();

        allHeading.add("Date");
        allHeading.add("Weather");
        allHeading.add("Temperature");
        allHeading.add("Humidity");
        allHeading.add("Wind Speed");
    }

    private void weatherStatusAPI() {
        WeatherAPIs weatherAPIs = apiBuilder();

        Call<CityModel> outputList =
                weatherAPIs.getFiveDayWeather(city, appid);

        mWeatherList = new ArrayList<>();
        mTempList = new ArrayList<>();
        mHumidityList = new ArrayList<>();
        mWindSpeedList = new ArrayList<>();

        mDateList = new ArrayList<>();

        outputList.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                if (response.isSuccessful()) {
                    Double mess = response.body().getMessage();

                    List<com.weathermap.helperClasses.List> allList = response.body().getList();

                    for (int i=0; i<allList.size(); i++) {

                        mDate = allList.get(0).getDtTxt();

                        mTemp = String.valueOf(Double.valueOf(String.valueOf(allList.get(i).getMain().getTemp())));
                        mWeatherStatus = String.valueOf(allList.get(0).getWeather().get(0).getDescription());
                        mHumidity = String.valueOf(allList.get(i).getMain().getHumidity());
                        mWindSpeed = String.valueOf(allList.get(i).getWind().getSpeed());

                        mDateList.add(mDate);

                        mWeatherList.add(mWeatherStatus);
                        mHumidityList.add(mHumidity);
                        mTempList.add(mTemp);
                        mWindSpeedList.add(mWindSpeed);
                    }

                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                int x = 11;
            }
        });
    }

    private WeatherAPIs apiBuilder() {
        WeatherServiceBuilder analogueServiceBuilder = new WeatherServiceBuilder(MainActivity.this,
                "3q3w4");
        return analogueServiceBuilder.buildService(WeatherAPIs.class, MainActivity.this);
    }

    private void initializingAPIValues() {
        latitude = "52.675960";
        longitude = "-7.602550";
        count = "3";
        appid = Constants.appID;
        city = "london";
    }

    //?lat=52.675960&lon=-7.602550&cnt=3&appid=b6907d289e10d714a6e88b30761fae22
    private void weatherAsPerCurrentLocationAndUnit() {
        WeatherAPIs weatherAPIs = apiBuilder();

        Call<WeatherAPIModel> outputList;

        SharedPreferences prefs = getSharedPreferences(
                "unit", Context.MODE_PRIVATE);
        boolean isButtonSelected = prefs.getBoolean("BUTTON_SELECTED", false);

        if (isButtonSelected) {
            outputList =
                    weatherAPIs.getWeatherAPIMetric(city, "metric", appid);
        } else {
            outputList =
                    weatherAPIs.getWeatherAPIImperial(city, "imperial", appid);
        }

        outputList.enqueue(new Callback<WeatherAPIModel>() {
            @Override
            public void onResponse(Call<WeatherAPIModel> call, Response<WeatherAPIModel> response) {
                if (response.isSuccessful()) {

                        Float temp = response.body().getTemp();
                        String h = String.valueOf(response.body().getHumidity());
                        String x = String.valueOf(response.body().getPressure());
                }
            }

            @Override
            public void onFailure(Call<WeatherAPIModel> call, Throwable t) {
                int x = 11;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent goToSettingsPage = new Intent(MainActivity.this, Settings.class);
            startActivity(goToSettingsPage);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

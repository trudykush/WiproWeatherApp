package com.weathermap.retrofitAPI;

import com.weathermap.helperClasses.CityModel;
import com.weathermap.helperClasses.CombinedHelperModel;
import com.weathermap.helperClasses.Main;
import com.weathermap.helperClasses.Rain;
import com.weathermap.helperClasses.Weather;
import com.weathermap.helperClasses.WeatherAPIModel;
import com.weathermap.helperClasses.Wind;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPIs {

    //?lat=52.675960&lon=-7.602550&cnt=3&appid=b6907d289e10d714a6e88b30761fae22
    @GET("data/2.5/find")
    Call<WeatherAPIModel> getWeatherAPI(@Query("lat") String lat,
                                              @Query("lon") String lon,
                                              @Query("cnt") String cnt,
                                              @Query("appid") String appid);

    @GET("data/2.5/find")
    Call<WeatherAPIModel> getWeatherAPIMetric(@Query("q") String q,
                                                    @Query("units") String units,
                                                    @Query("appid") String appid);

    @GET("data/2.5/find")
    Call<WeatherAPIModel> getWeatherAPIImperial(@Query("q") String q,
                                              @Query("units") String units,
                                              @Query("appid") String appid);

    @GET("data/2.5/forecast")
    Call<CityModel> getFiveDayWeather(@Query("q") String q,
                                      @Query("appid") String appid);

    /*@GET("data/2.5/forecast")
    Call<List<com.weathermap.helperClasses.List>> getFiveDayWeather(@Query("q") String q,
                                                                   @Query("appid") String appid);*/

    @GET("data/2.5/forecast")
    Call<List<Main>> getTemp(@Query("q") String q,
                             @Query("appid") String appid);

    @GET("data/2.5/forecast")
    Call<List<Wind>> getWind(@Query("q") String q,
                             @Query("appid") String appid);
}

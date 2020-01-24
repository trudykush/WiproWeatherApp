package com.weathermap.retrofitAPI;

import android.content.Context;

import com.weathermap.global.API;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceBuilder {

    public Context mContext;
    private Retrofit retrofit;
    private Retrofit.Builder retrofitBuilder;
    private String accessToken;

    private String URL_VIA_COORDINATES;

    public WeatherServiceBuilder(Context mContext, String accessToken) {
        this.mContext = mContext;
        this.accessToken = accessToken;

        OkHttpClient gettingOkHttpClient = getOkHttp();

        URL_VIA_COORDINATES = API.MAIN_URL;
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(URL_VIA_COORDINATES)
                .client(gettingOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public <S> S buildService(Class<S> serviceType, Context context) {
        retrofit = retrofitBuilder.build();
        return retrofit.create(serviceType);
    }

    private OkHttpClient getOkHttp() {

        HttpLoggingInterceptor logger =
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient;

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

}

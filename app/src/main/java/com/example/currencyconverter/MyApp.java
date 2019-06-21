package com.example.currencyconverter;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MyApp extends Application
{
    private static APIService apiService;

    @Override
    public void onCreate()
    {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.cbr.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public static APIService getApi()
    {
        return apiService;
    }
}
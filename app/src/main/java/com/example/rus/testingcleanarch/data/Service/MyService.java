package com.example.rus.testingcleanarch.data.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


import com.example.rus.testingcleanarch.data.DataBase.DBManager;
import com.example.rus.testingcleanarch.data.Entity.Weather5;
import com.example.rus.testingcleanarch.data.Entity.WeatherDay;
import com.example.rus.testingcleanarch.data.Network.WeatherAPI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends IntentService {

    WeatherAPI.ApiInterface api;
    DBManager dbManager;

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);


        Call<Weather5> weather5Call = api.getForecast(WeatherAPI.CITY_ID, "metric",WeatherAPI.KEY);
        weather5Call.enqueue(new Callback<Weather5>() {
            @Override
            public void onResponse(Call<Weather5> call, Response<Weather5> response) {
                Weather5 weather5 = response.body();
                List<WeatherDay> weatherDayList = new ArrayList<>();
                for (WeatherDay weatherDay : weather5.getItems()){
                    if (weatherDay.getDateCalendar().get(Calendar.HOUR_OF_DAY) == 15){
                        weatherDayList.add(weatherDay);
                    }
                }
                dbManager = new DBManager(getBaseContext());
                dbManager.addWeatherList(weatherDayList);
                Intent broadcastIntent = new Intent("myservice");
                broadcastIntent.putExtra("result","response");
                sendBroadcast(broadcastIntent);
            }

            @Override
            public void onFailure(Call<Weather5> call, Throwable t) {

            }
        });
    }
}

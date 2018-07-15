package com.example.rus.testingcleanarch.data.Repository;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.rus.testingcleanarch.data.DataBase.DBManager;
import com.example.rus.testingcleanarch.data.Entity.Weather5;
import com.example.rus.testingcleanarch.data.Mapper.WeatherDayMapper;
import com.example.rus.testingcleanarch.data.Network.WeatherAPI;
import com.example.rus.testingcleanarch.domain.WeatherDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataProvider {

    private List<com.example.rus.testingcleanarch.data.Entity.WeatherDay> weatherDayListEntity;
    DBManager dbManager;

    public List<WeatherDay> getWeatherDayList(DBManager dbManager, final Context context) {
        List<WeatherDay> weatherDayList = new ArrayList<>();
        WeatherDayMapper mapper = new WeatherDayMapper();
        this.dbManager = dbManager;
        weatherDayListEntity = new ArrayList<>();
        weatherDayListEntity = DataProvider.this.dbManager.getWeatherDaysList();

        WeatherAPI.ApiInterface api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
        Call<Weather5> weather5Call = api.getForecast(WeatherAPI.CITY_ID, "metric",WeatherAPI.KEY);
        weather5Call.enqueue(new Callback<Weather5>() {
            @Override
            public void onResponse(Call<Weather5> call, Response<Weather5> response) {
                Log.v("TAG", "RECIEVED");
                Weather5 weather5 = response.body();
                for (com.example.rus.testingcleanarch.data.Entity.WeatherDay weatherDay : weather5.getItems()){
                    if (weatherDay.getDateCalendar().get(Calendar.HOUR_OF_DAY) == 15){
                        weatherDayListEntity.add(weatherDay);
                        Log.v("TAG", "add in weatherDayList");
                    }
                }
                //dbManager = new DBManager(getBaseContext());
                DataProvider.this.dbManager.addWeatherList(weatherDayListEntity);
                Log.v("TAG", "Added in DB");


                //testing
                Intent broadcastIntent = new Intent("myservice");
                broadcastIntent.putExtra("result","response");
                context.sendBroadcast(broadcastIntent);

            }

            @Override
            public void onFailure(Call<Weather5> call, Throwable t) {
                Log.v("TAG", "Failed");
            }
        });
        Log.v("TAG", "Test");

        weatherDayList = mapper.transform(weatherDayListEntity);
        Log.v("TAG", "Weather day list" + weatherDayList.size());
        return weatherDayList;
    }

    public List<WeatherDay> getWeatherDayList(DBManager dbManager) {
        List<WeatherDay> weatherDayList = new ArrayList<>();
        WeatherDayMapper mapper = new WeatherDayMapper();
        this.dbManager = dbManager;
        weatherDayListEntity = new ArrayList<>();
        weatherDayListEntity = DataProvider.this.dbManager.getWeatherDaysList();
        weatherDayList = mapper.transform(weatherDayListEntity);
        Log.v("TAG", "Weather day list" + weatherDayList.size());
        return weatherDayList;
    }
}

package com.example.rus.testingcleanarch.domain.Interactor;

import android.content.Context;

import com.example.rus.testingcleanarch.data.DataBase.DBManager;
import com.example.rus.testingcleanarch.data.Repository.DataProvider;
import com.example.rus.testingcleanarch.domain.WeatherDay;
import com.example.rus.testingcleanarch.presentation.Model.WeatherDayModel;

import java.util.ArrayList;
import java.util.List;

public class GetWeatherDayList {

    DataProvider dataProvider = new DataProvider();

    public List<WeatherDay> getWeatherDayList(DBManager dbManager, Context context) {
        List<WeatherDay> weatherDayList = new ArrayList<>();
        weatherDayList = dataProvider.getWeatherDayList(dbManager, context);
        return weatherDayList;
    }

    public List<WeatherDay> getWeatherDayList(DBManager dbManager) {
        List<WeatherDay> weatherDayList = new ArrayList<>();
        weatherDayList = dataProvider.getWeatherDayList(dbManager);
        return weatherDayList;
    }
}

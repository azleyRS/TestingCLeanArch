package com.example.rus.testingcleanarch.presentation.Presenter;

import com.example.rus.testingcleanarch.data.DataBase.DBManager;
import com.example.rus.testingcleanarch.domain.Interactor.GetWeatherDayList;
import com.example.rus.testingcleanarch.presentation.Mapper.WeatherDayModelDataMapper;
import com.example.rus.testingcleanarch.presentation.Model.WeatherDayModel;
import com.example.rus.testingcleanarch.presentation.View.MainView;
import com.example.rus.testingcleanarch.presentation.View.adapter.MyAdapter;

import java.util.List;

public class MainPresenter {
    private final DBManager dbManager;
    private MainView mainView;
    GetWeatherDayList getWeatherDayList = new GetWeatherDayList();
    WeatherDayModelDataMapper weatherDayModelDataMapper = new WeatherDayModelDataMapper();

    public MainPresenter(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void attachView(MainView mainActivity) {
        mainView = mainActivity;
    }


    public void setUpAdapter(MyAdapter adapter) {
        List<WeatherDayModel> weatherDayModelList = weatherDayModelDataMapper.transform(getWeatherDayList.getWeatherDayList(dbManager));
        adapter.update(weatherDayModelList);
        adapter.notifyDataSetChanged();
    }
}

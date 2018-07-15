package com.example.rus.testingcleanarch.presentation.View.activity.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rus.testingcleanarch.R;
import com.example.rus.testingcleanarch.data.DataBase.DBManager;
import com.example.rus.testingcleanarch.data.Entity.Weather5;
import com.example.rus.testingcleanarch.data.Entity.WeatherDay;
import com.example.rus.testingcleanarch.data.Network.WeatherAPI;
import com.example.rus.testingcleanarch.presentation.Presenter.MainPresenter;
import com.example.rus.testingcleanarch.presentation.View.MainView;
import com.example.rus.testingcleanarch.presentation.View.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    //private MainAdapter adapter;
    private WeatherAPI.ApiInterface api;
    private DBManager dbManager;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        dbManager = new DBManager(getBaseContext());

        mainPresenter = new MainPresenter(dbManager);
        mainPresenter.attachView(this);
        mainPresenter.setUpAdapter(adapter);

        makeShit();
    }

    private void makeShit() {
        /*api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);

        //delete later
        dbManager = new DBManager(getBaseContext());

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
                //dbManager = new DBManager(getBaseContext());
                dbManager.addWeatherList(weatherDayList);

                adapter.update(dbManager.getWeatherDaysList());
                adapter.notifyDataSetChanged();
                *//*Intent broadcastIntent = new Intent("myservice");
                broadcastIntent.putExtra("result","response");
                sendBroadcast(broadcastIntent);*//*
            }

            @Override
            public void onFailure(Call<Weather5> call, Throwable t) {
                adapter.update(dbManager.getWeatherDaysList());
                adapter.notifyDataSetChanged();
            }
        });*/
    }
}

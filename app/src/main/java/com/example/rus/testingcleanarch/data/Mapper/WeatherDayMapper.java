package com.example.rus.testingcleanarch.data.Mapper;

import com.example.rus.testingcleanarch.data.Entity.WeatherDay;

import java.util.ArrayList;
import java.util.List;

public class WeatherDayMapper {
    public com.example.rus.testingcleanarch.domain.WeatherDay transform(WeatherDay weatherDay){
        com.example.rus.testingcleanarch.domain.WeatherDay weatherDayDomain = new com.example.rus.testingcleanarch.domain.WeatherDay();
        weatherDayDomain.setTimestamp(weatherDay.getTimestamp());
        weatherDayDomain.setGrnd_level(weatherDay.getGrnd_level());
        weatherDayDomain.setTemp(Double.valueOf(weatherDay.getTemp()));
        weatherDayDomain.setTemp_max(Double.valueOf(weatherDay.getTempMax()));
        weatherDayDomain.setTemp_min(Double.valueOf(weatherDay.getTempMin()));
        weatherDayDomain.setPressure(weatherDay.getPressure());
        weatherDayDomain.setSea_level(weatherDay.getSea_level());
        weatherDayDomain.setHumidity(weatherDay.getHumidity());
        weatherDayDomain.setTemp_kf(weatherDay.getTemp_kf());
        return weatherDayDomain;
    }

    public List<com.example.rus.testingcleanarch.domain.WeatherDay> transform(List<WeatherDay> weatherDayCollection){
        List<com.example.rus.testingcleanarch.domain.WeatherDay> weatherDayDomainCollection = new ArrayList<>();
        for (WeatherDay weatherDay : weatherDayCollection){
            weatherDayDomainCollection.add(transform(weatherDay));
        }
        return weatherDayDomainCollection;
    }
}

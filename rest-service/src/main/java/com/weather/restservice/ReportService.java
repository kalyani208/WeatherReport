package com.weather.restservice;

import com.weather.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weather.repository.WeatherRepository;

@Service
public class ReportService
{
    @Autowired
    WeatherRepository weatherRepository;

    public Report getWeather(String city)
    {
        return weatherRepository.findByCityname(city);
    }
    public void saveOrUpdate(Report report)
    {
        weatherRepository.save(report);
    }

}
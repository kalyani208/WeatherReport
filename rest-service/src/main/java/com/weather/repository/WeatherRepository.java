package com.weather.repository;

import com.weather.model.Report;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends CrudRepository<Report, String> {

    Report findByCityname(String cityname);


}


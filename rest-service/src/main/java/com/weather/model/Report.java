package com.weather.model;

import javax.persistence.*;

@Entity
@Table
public class Report
{
    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column
    private String cityname;
    @Column
    private String description;

    public Report(){
    }
    public Report(String cityname, String description){
        this.cityname = cityname;
        this.description = description;
    }

}
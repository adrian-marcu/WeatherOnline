package com.example.dto;

import com.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by admarcu on 11/30/2016.
 */
public class WeatherDTO implements Serializable {


    @Autowired
    WeatherService weatherService;

    private String day;
    private String temp;
    private String humidity;
    private String pressure;
    private String windspeed;
    private String sky;

    public WeatherDTO(String day, String temp, String humidity, String pressure, String windspeed, String sky) {
        this.day = day;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windspeed = windspeed;
        this.sky = sky;
    }

    public WeatherDTO() {
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }
}

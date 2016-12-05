package com.example.service;

import com.example.model.City;
import com.example.repository.CityRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by admarcu on 11/26/2016.
 */
public class WeatherServiceTest {


    @Test
    public void testFuction() throws Exception {
        WeatherService s = new WeatherService();

        s.testFuction();
    }

    @Test
    public void parsedJSON() throws Exception {
        WeatherService s= new WeatherService();

    }

}
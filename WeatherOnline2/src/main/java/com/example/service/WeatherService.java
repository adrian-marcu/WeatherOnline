package com.example.service;

import com.example.dto.WeatherDTO;
import com.example.model.City;
import com.example.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;


import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by admarcu on 11/21/2016.
 */
@Service
public class WeatherService {
    @Autowired
    CityRepository cityRepository;
    public static final String appid = "e1d32089125b10e50f02b1e3c819bdad";

    public String URLconstruct(String city) {
        String URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
        URL += city;
        URL += "&mode=json&units=metric&appid=";
        URL += appid;
        return URL;
    }

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);

            return json;
        } finally {
            is.close();
        }
    }

    public String getJSON(String city) throws IOException {
        JSONObject json = readJsonFromUrl(URLconstruct(city));
        return json.toString();
    }

    public void testFuction(){
/*        City foundCity=cityRepository.findByCityId(40);
        Date newDate=new Date();
        Date dbDate=foundCity.getLastUpdated();

        System.out.println(newDate.getTime());
        System.out.println("******");
        System.out.println(dbDate.getTime());*/
    }

    public List<WeatherDTO> ParsedJSON(JSONObject myjson) throws IOException {

        List<WeatherDTO> weatherDTOs=new ArrayList<>();

        System.out.println("City Name=" + myjson.getJSONObject("city").get("name")); //city name
        System.out.println("Country=" + myjson.getJSONObject("city").get("country"));
        String daydate = "";
        for (int i = 0; i < myjson.getJSONArray("list").length(); i++) {
            WeatherDTO dt=new WeatherDTO();
            long Seconds = (Integer) myjson.getJSONArray("list").getJSONObject(i).get("dt");
            Date date2 = new Date(Seconds * 1000L);
            SimpleDateFormat sdfz = new SimpleDateFormat("dd");
            sdfz.setTimeZone(TimeZone.getTimeZone("GMT-4"));
            String formattedDate2 = sdfz.format(date2);
            if (!daydate.equals(formattedDate2)) {
                System.out.println("Day=" + formattedDate2);
                Float tempint = Float.parseFloat(myjson.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("temp").toString());
                String humidity = myjson.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("humidity").toString();
                String pressure = myjson.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("pressure").toString();
                String windspeed = myjson.getJSONArray("list").getJSONObject(i).getJSONObject("wind").get("speed").toString();
                String sky = myjson.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).get("description").toString();
                System.out.println("Temperature=" + tempint + " Humidity=" + humidity + " Pressure=" + pressure + " Wind Speed=" + windspeed); //temperatura
                daydate = formattedDate2;
                SimpleDateFormat arc = new SimpleDateFormat("dd-MM-yyyy");
                arc.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String databuna = arc.format(date2);
                dt.setDay(databuna);
                dt.setTemp(myjson.getJSONArray("list").getJSONObject(i).getJSONObject("main").get("temp").toString());
                dt.setHumidity(humidity);
                dt.setPressure(pressure);
                dt.setWindspeed(windspeed);
                dt.setSky(sky);
                weatherDTOs.add(dt);
            }
        }


        return weatherDTOs;
    }




}

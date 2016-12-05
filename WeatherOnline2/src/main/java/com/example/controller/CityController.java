package com.example.controller;

import com.example.dto.WeatherDTO;
import com.example.model.City;
import com.example.model.User;
import com.example.repository.CityRepository;
import com.example.repository.UserRepository;
import com.example.service.WeatherService;
import org.hibernate.mapping.Set;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by admarcu on 11/26/2016.
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {

    private static final int updateTime = 86400;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeatherService weatherService;

    @RequestMapping(method = RequestMethod.GET)
    public List<City> findAll() {

        return cityRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public City addCity(@RequestBody City city) {
        return cityRepository.saveAndFlush(city);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public City addCityForUser(@RequestBody City city, @PathVariable String username) throws IOException {
        System.out.println("In addCityFOrUser");
        System.out.println(city.getCityName());
        System.out.println(username);

        User u = userRepository.findByUsername(username);
        city.getUsers().add(u);
        city.setJson(weatherService.getJSON(city.getCityName())); // seteaza jsonu orasului
        city.setLastUpdated(new Date());
        if (cityRepository.findByName(city.getCityName()) != null) {
            City foundCity = cityRepository.findByName(city.getCityName());
            foundCity.getUsers().add(u);
            //System.out.println("CITY FOUND!");
            cityRepository.save(foundCity);
            return foundCity;
        }
        return cityRepository.save(city);
    }


    @RequestMapping(value = "/{cityId}/{username}", method = RequestMethod.DELETE)
    public void deleteCity(@PathVariable Integer cityId, @PathVariable String username) {
        if (cityRepository.findByCityId(cityId).getUsers().size() == 1) {
            cityRepository.delete(cityId);
        } else {
            City foundCity = cityRepository.findByCityId(cityId);
            User foundUser = userRepository.findByUsername(username);
            foundCity.getUsers().remove(foundUser);
            System.out.println(foundCity.getUsers().isEmpty());
            cityRepository.save(foundCity);
        }
    }


    @RequestMapping(value = "/{cityId}", method = RequestMethod.PUT)
    public void updateCity(@RequestBody City city, @PathVariable Integer cityId) {
        city.setCityName(city.getCityName());
        cityRepository.saveAndFlush(city);
    }


    @RequestMapping(value = "/getWeather", method = RequestMethod.POST)
    public List<WeatherDTO> getWeatherForCity(@RequestBody Integer cityId) throws IOException {

        City foundCity = cityRepository.findByCityId(cityId);
        Date newDate = new Date();
        Date dbDate = foundCity.getLastUpdated();

        System.out.println(newDate.getTime() / 1000);
        System.out.println("******");
        System.out.println(dbDate.getTime() / 1000);
        System.out.println(newDate.getTime() / 1000 - dbDate.getTime() / 1000);

        if ((newDate.getTime() / 1000 - dbDate.getTime() / 1000) > updateTime) {
            System.out.println("I-A FACUT UPDATE LA JSON");
            foundCity.setJson(weatherService.getJSON(foundCity.getCityName())); // seteaza jsonu orasului
            foundCity.setLastUpdated(new Date());// seteaza data curenta
            cityRepository.save(foundCity); // update in baza de date
        }

        JSONObject o = new JSONObject(foundCity.getJson());
        // System.out.println(weatherService.ParsedJSON(o).toString());
        return weatherService.ParsedJSON(o);
    }


}

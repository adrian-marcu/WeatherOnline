package com.example.repository;

import com.example.model.City;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admarcu on 11/23/2016.
 */
@Repository
public interface CityRepository extends JpaRepository<City,Integer>{
    City findByName(String name);
    City findByCityId(Integer cityId);
    List<City> findAllByUsers(User user);
    List<City> findByUsers_username(String username);

}

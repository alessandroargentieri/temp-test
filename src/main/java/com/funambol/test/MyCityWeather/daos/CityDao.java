package com.funambol.test.MyCityWeather.daos;

import com.funambol.test.MyCityWeather.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityDao extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
}

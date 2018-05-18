package com.funambol.test.MyCityWeather.daos;

import com.funambol.test.MyCityWeather.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperatureDao extends JpaRepository<Temperature, Long> {
    List<Temperature> findByFkCity(Integer fkCity);
}

package com.funambol.test.MyCityWeather.controllers;

import com.funambol.test.MyCityWeather.daos.CityDao;
import com.funambol.test.MyCityWeather.daos.TemperatureDao;
import com.funambol.test.MyCityWeather.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TempController {

    @Autowired
    CityDao cityDao;

    @Autowired
    TemperatureDao temperatureDao;

    @RequestMapping("/get/temperatures/{city}")
    public List<Temperature> getTemps(@PathVariable(name="city") String cityName){
       return temperatureDao.findByFkCity(cityDao.findByName(cityName).get().getIdCity());
    }


}

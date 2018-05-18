package com.funambol.test.MyCityWeather.controllers;

import com.funambol.test.MyCityWeather.daos.CityDao;
import com.funambol.test.MyCityWeather.daos.TemperatureDao;
import com.funambol.test.MyCityWeather.entities.City;
import com.funambol.test.MyCityWeather.entities.Temperature;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TempControllerTest {

    TempController tempController;
    TemperatureDao temperatureDao;
    CityDao cityDao;

    @Before
    public void setUp(){
        tempController = new TempController();
        temperatureDao = mock(TemperatureDao.class);
        cityDao = mock(CityDao.class);

        ReflectionTestUtils.setField(tempController, "temperatureDao", temperatureDao);
        ReflectionTestUtils.setField(tempController, "cityDao", cityDao);
    }

    @Test
    public void getTempsTest(){
        List<Temperature> results = new ArrayList<>();
        results.add(new Temperature(1L, 123.34, null, 1));
        results.add(new Temperature(2L, 133.54, null, 1));
        results.add(new Temperature(3L, 97.12, null, 1));

        Optional<City> cityOptional = Optional.of(new City(1, "pavia"));

        when(cityDao.findByName("pavia")).thenReturn(cityOptional);
        when(temperatureDao.findByFkCity(1)).thenReturn(results);

        assertEquals(tempController.getTemps("pavia"), results);
    }



}
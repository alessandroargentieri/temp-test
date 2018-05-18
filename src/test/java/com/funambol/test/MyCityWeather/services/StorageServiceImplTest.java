package com.funambol.test.MyCityWeather.services;

import com.funambol.test.MyCityWeather.daos.CityDao;
import com.funambol.test.MyCityWeather.daos.TemperatureDao;
import com.funambol.test.MyCityWeather.entities.City;
import mockit.MockUp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StorageServiceImplTest {

    StorageService storageService;
    CityDao cityDao;
    TemperatureDao temperatureDao;


    @Before
    public void setUp(){
        storageService = new StorageServiceImpl();
        cityDao = mock(CityDao.class);
        temperatureDao = mock(TemperatureDao.class);
        ReflectionTestUtils.setField(storageService, "cityDao", cityDao);
        ReflectionTestUtils.setField(storageService, "temperatureDao", temperatureDao);

    }

    @Test
    public void componentInjectionTest(){
        assertNotNull(ReflectionTestUtils.getField(storageService, "cityDao"));
        assertNotNull(ReflectionTestUtils.getField(storageService, "temperatureDao"));
    }

    @Test
    public void updateProcedure(){

        List<City> mockedCities = new ArrayList<>();
        mockedCities.add(new City(1, "pavia"));
        mockedCities.add(new City(2, "milano"));
        mockedCities.add(new City(3, "bergamo"));

        when(cityDao.findAll()).thenReturn(mockedCities);
        when(cityDao.findByName(any(String.class))).thenReturn(Optional.of(new City(1, "pavia")));

        new MockUp<StorageServiceImpl>(){
            @mockit.Mock
            public Double getTheTempGivenTheCityName(String cityName){
                return 121.45;
            }
        };

        assertEquals(1,storageService.updateDbProcedure());

    }
}
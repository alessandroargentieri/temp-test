package com.funambol.test.MyCityWeather.services;

import com.funambol.test.MyCityWeather.daos.CityDao;
import com.funambol.test.MyCityWeather.daos.TemperatureDao;
import com.funambol.test.MyCityWeather.entities.City;
import com.funambol.test.MyCityWeather.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class StorageServiceImpl implements StorageService{

    @Autowired CityDao cityDao;

    @Autowired TemperatureDao temperatureDao;

    private final static String BASIC_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final static String API_KEY = "b833ce501ff196a419ba285594863c6c";

    public int updateDbProcedure(){
        try {
            List<City> cities = cityDao.findAll();
            for (City city : cities) {
                String cityName = city.getName();
                Double temp = getTheTempGivenTheCityName(cityName);
                saveInDb(cityName, temp);
            }
        }catch(Exception e){
            return 0;
        }
        return 1;
    }

    private Double getTheTempGivenTheCityName(String cityName){
        String url = BASIC_URL+"?q="+cityName+"&appid="+API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
        Map mapOfResponse = (Map) responseEntity.getBody();
        Map subMap = (Map) mapOfResponse.get("main");
        Double temp = (Double) subMap.get("temp");
        System.out.println("temp: " + temp);
        return temp;
    }

    private void saveInDb(String cityName, Double temp){
        temperatureDao.save(new Temperature(null, temp, null, cityDao.findByName(cityName).get().getIdCity()));
    }



}

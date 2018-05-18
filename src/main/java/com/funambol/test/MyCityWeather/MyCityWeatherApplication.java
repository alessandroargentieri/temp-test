package com.funambol.test.MyCityWeather;

import com.funambol.test.MyCityWeather.daos.CityDao;
import com.funambol.test.MyCityWeather.entities.City;
import com.funambol.test.MyCityWeather.services.StorageService;
import com.funambol.test.MyCityWeather.task.StorageTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Timer;

@SpringBootApplication
public class MyCityWeatherApplication {

	@Autowired
	CityDao cityDao;

	@Autowired
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(MyCityWeatherApplication.class, args);
	}

	@PostConstruct
	private void fillTheDatabase(){
		cityDao.save(new City(null, "pavia"));
		cityDao.save(new City(null, "milano"));
		cityDao.save(new City(null, "mantova"));
		cityDao.save(new City(null, "venezia"));
		cityDao.save(new City(null, "vicenza"));
		cityDao.save(new City(null, "lodi"));

		//for the first time at least not waiting 12.00 o-clock in test phase
		storageService.updateDbProcedure();

		StorageTask.startTask();

	}
}

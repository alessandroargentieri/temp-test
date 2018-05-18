package com.funambol.test.MyCityWeather.task;

import com.funambol.test.MyCityWeather.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class StorageTask extends TimerTask {

    private final static int TIME = 12;
    private final static int ZERO_MINUTES = 0;

    @Autowired
    StorageService storageService;

    @Override
    public void run() {
        long currennTime = System.currentTimeMillis();
        long stopTime = currennTime + 200000;
        while(stopTime != System.currentTimeMillis()){
            storageService.updateDbProcedure();
        }
    }

    private static Date getTomorrowMorning2AM(){
        Date date2am = new java.util.Date();
        date2am.setHours(TIME);
        date2am.setMinutes(ZERO_MINUTES);
        return date2am;
    }

    public static void startTask(){
        StorageTask task = new StorageTask();
        Timer timer = new Timer();
        timer.schedule(task,getTomorrowMorning2AM(),1000*10);
    }

    public static void main(String args[]){
        startTask();
    }

}
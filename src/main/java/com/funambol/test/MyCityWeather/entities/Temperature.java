package com.funambol.test.MyCityWeather.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity @Table(name="temperature")
@AllArgsConstructor @NoArgsConstructor @Data
public class Temperature {

    @Id @Column(name="idtemperature") @GeneratedValue
    private Long idTemperature;

    @Column(name="temp") @NotNull
    private Double temp;

    @Column(name="timestamp")
    private Date timestamp;

    @Column(name="fkcity")
    private Integer fkCity;

    @PrePersist
    private void setTheTimestamp(){
        timestamp = new Date();
    }
}

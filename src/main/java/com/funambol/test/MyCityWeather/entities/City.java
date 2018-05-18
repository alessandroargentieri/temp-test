package com.funambol.test.MyCityWeather.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity @Table(name="city")
@AllArgsConstructor @NoArgsConstructor @Data
public class City {

    @Id @Column(name="idcity") @GeneratedValue
    private Integer idCity;

    @Column(name="name")
    private String name;

}

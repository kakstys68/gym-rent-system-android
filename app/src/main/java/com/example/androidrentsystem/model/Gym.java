package com.example.androidrentsystem.model;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gym implements Serializable {
    private int id;
    private String name;
    private String address;
    private double hourlyPrice;
    private String type;

    public Gym(String name, String address, double hourlyPrice, String type) {
        this.name = name;
        this.address = address;
        this.hourlyPrice = hourlyPrice;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " " + address;
    }
}

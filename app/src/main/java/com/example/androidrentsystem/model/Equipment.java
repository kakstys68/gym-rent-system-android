package com.example.androidrentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private int id;
    private String name;
    private String sportType;
    private int quantity;



    //@ManyToOne
    //private Gym gym;

    public Equipment(String name, String sportType, int quantity) {
        this.name = name;
        this.sportType = sportType;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " " + sportType;
    }

}

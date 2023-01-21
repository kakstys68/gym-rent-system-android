package com.example.androidrentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    private int id;
    private LocalDate orderDate;
    //private OrderStatus status;
    private User buyer;
    //CIA PAKLAUSTI DEL RYSIO
    //@OneToOne
    //private Gym gym;

}

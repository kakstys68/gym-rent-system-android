package com.example.androidrentsystem.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Client extends User implements Serializable {

    LocalDate birthDate;
    private List<Orders> myOrders;
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Client(String username, String password, String name, String surname, String email, LocalDate birthDate) {
        super(username, password, name, surname, email);
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return getUsername() + ", " + getName() + " " + getSurname();
    }
}

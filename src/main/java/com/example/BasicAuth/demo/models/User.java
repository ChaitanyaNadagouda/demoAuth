package com.example.BasicAuth.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User extends BaseModel {


    private String Name ;
    private String email ;
    private String password ;
    private String phoneNumber ;

    public User() {

    }
}
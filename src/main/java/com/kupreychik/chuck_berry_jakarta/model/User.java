package com.kupreychik.chuck_berry_jakarta.model;

import com.kupreychik.chuck_berry_jakarta.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
//@Setter
//@AllArgsConstructor
public class User {
    private static int idCounter = 0;
    private final int id;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private UserRole role;

    public User(String name, String email, UserRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
        id = idCounter++;
    }

}

/*
@Getter
@ToString
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private UserRole role;
}*/

package com.kupreychik.chuck_berry_jakarta.model;

import com.kupreychik.chuck_berry_jakarta.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private UserRole role;
}

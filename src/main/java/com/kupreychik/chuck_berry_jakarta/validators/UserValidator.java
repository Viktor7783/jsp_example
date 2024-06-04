package com.kupreychik.chuck_berry_jakarta.validators;

import com.kupreychik.chuck_berry_jakarta.model.User;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UserValidator {


    public boolean isEmailExists(String email, List<User> users) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean isNameExists(String name, List<User> users) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }
}

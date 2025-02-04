package com.kupreychik.chuck_berry_jakarta.service;


import com.kupreychik.chuck_berry_jakarta.model.User;
import com.kupreychik.chuck_berry_jakarta.model.enums.UserRole;
import com.kupreychik.chuck_berry_jakarta.validators.UserValidator;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.kupreychik.chuck_berry_jakarta.model.enums.UserRole.*;

@Getter
public class UserService {
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final UserValidator validator;
    private final ResourceBundle messages;

    public UserService(UserValidator validator, ResourceBundle messages) {
        this.validator = validator;
        this.messages = messages;
        fillDefaultUsers();
    }

    public void addUser(User user) {
        users.add(user);
    }

   /* public Optional<User> getUser(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }*/

    public Optional<User> getUserById(String userId) {
        return users.stream()
                .filter(user -> String.valueOf(user.getId()).equals(userId))
                .findFirst();
    }

    public void updateUser(String userId, String newName, String newEmail, UserRole newRole) {
        getUserById(userId).ifPresent(user -> {
            user.setName(newName);
            user.setEmail(newEmail);
            user.setRole(newRole);
        });
    }

    public void deleteUser(String userId) {
        users.removeIf(user -> String.valueOf(user.getId()).equals(userId));
    }

    public boolean isEmailValid(String email) {
        return validator.isEmailExists(email, this.users);
    }

    public boolean isNameValid(String name) {
        return validator.isNameExists(name, this.users);
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }

    private void fillDefaultUsers() {
        users.add(new User("Anton", "email@email.com", ADMIN));
        users.add(new User("Ivan", "iavn@email.com", CUSTOMER));
        users.add(new User("Sergey", "serget@email.com", MANAGER));
        users.add(new User("Katya", "katya@email.com", CUSTOMER));
    }
}

package com.kupreychik.chuck_berry_jakarta.listener;

import com.kupreychik.chuck_berry_jakarta.service.UserService;
import com.kupreychik.chuck_berry_jakarta.validators.UserValidator;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.USER_SERVICE;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

        UserValidator userValidator = new UserValidator();
        UserService userService = new UserService(userValidator, resourceBundle);

        ctx.setAttribute(USER_SERVICE, userService);

        ServletContextListener.super.contextInitialized(sce);
    }
}

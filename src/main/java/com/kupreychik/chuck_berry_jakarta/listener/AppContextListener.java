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

/*
    @WebListener - указывает, что класс является слушателем событий веб-приложения
    Реализует ServletContextListener - интерфейс для отслеживания жизненного цикла приложения
    Метод contextInitialized() вызывается при запуске приложения
    Создает и настраивает все необходимые сервисы один раз при старте
    Сохраняет сервисы в контексте приложения для дальнейшего использования
*/
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Получаем контекст сервлета
        ServletContext ctx = sce.getServletContext();
        // Загружаем файл с сообщениями (обычно messages.properties)
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
        // Создаем необходимые сервисы при старте приложения
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserService(userValidator, resourceBundle);
        // Сохраняем сервис в контексте приложения
        ctx.setAttribute(USER_SERVICE, userService);
        // Вызываем родительский метод
        ServletContextListener.super.contextInitialized(sce);
    }
}

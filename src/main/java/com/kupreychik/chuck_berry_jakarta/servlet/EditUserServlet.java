package com.kupreychik.chuck_berry_jakarta.servlet;

import com.kupreychik.chuck_berry_jakarta.model.User;
import com.kupreychik.chuck_berry_jakarta.model.enums.UserRole;
import com.kupreychik.chuck_berry_jakarta.service.UserService;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.*;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.USERS;

@Slf4j
@WebServlet(SLASH + USERS + SLASH + EDIT)
public class EditUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String name = request.getParameter(NAME);
        String userId = request.getParameter(USER_ID);
        //Optional<User> user = userService.getUser(name);
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            request.setAttribute(USER, user.get());
            request.getRequestDispatcher(EDIT_USER_JSP).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + SLASH + USERS);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter(USER_ID);
        String newName = request.getParameter(NAME);
        String newEmail = request.getParameter(EMAIL);
        String oldEmail = request.getParameter(OLD_EMAIL);
        String role = request.getParameter(ROLE);

        log.info("Updating user with id: {} to new name: {} and new email: {}", userId, newName, newEmail);
        userService.updateUser(userId, newName, newEmail, UserRole.valueOf(role));
        response.sendRedirect(request.getContextPath() + SLASH + USERS);
    }
}

/*sendRedirect используется в response, потому что это правильный способ перенаправления пользователя на другую страницу после выполнения POST-запроса.
Это соответствует паттерну PRG (Post/Redirect/Get), который предотвращает повторную отправку формы при обновлении страницы.
Преимущества использования sendRedirect в response:
    Браузер получает код 302 (редирект) и автоматически выполняет новый GET-запрос по указанному URL
    Предотвращает дублирование данных при обновлении страницы
    После редиректа URL в браузере обновляется на новый
    Пользователь не видит промежуточных данных обработки POST-запроса
В вашем коде после обновления данных пользователя происходит перенаправление на страницу со списком пользователей:
response.sendRedirect(request.getContextPath() + SLASH + USERS);
*/
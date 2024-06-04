package com.kupreychik.chuck_berry_jakarta.servlet;

import com.kupreychik.chuck_berry_jakarta.model.User;
import com.kupreychik.chuck_berry_jakarta.model.enums.UserRole;
import com.kupreychik.chuck_berry_jakarta.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.CREATE_USER_JSP;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.EMAIL;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.ERROR;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.ERROR_EMAIL_EXISTS;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.ERROR_NAME_EXISTS;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.NAME;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.ROLE;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.ROLES;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.SLASH;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.USERS;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.CREATE;

@Slf4j
@WebServlet(SLASH + USERS + SLASH + CREATE)
public class CreateUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("CreateUserServlet doGet");
        request.setAttribute(ROLES, UserRole.values());
        request.getRequestDispatcher(CREATE_USER_JSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME);
        String email = request.getParameter(EMAIL);
        UserRole role = UserRole.valueOf(request.getParameter(ROLE));

        if (userService.isEmailValid(email)) {
            request.setAttribute(ERROR, userService.getMessage(ERROR_EMAIL_EXISTS));

            request.setAttribute(NAME, name);
            request.setAttribute(EMAIL, email);
            request.setAttribute(ROLES, UserRole.values());

            request.getRequestDispatcher(CREATE_USER_JSP).forward(request, response);
            return;
        }
        if (userService.isNameValid(name)) {
            request.setAttribute("error", userService.getMessage(ERROR_NAME_EXISTS));
            request.getRequestDispatcher(CREATE_USER_JSP).forward(request, response);
            return;
        }

        log.info("Creating new user with data: name {}, email {}, role {}", name, email, role);
        User user = new User(name, email, role);
        userService.addUser(user);

        response.sendRedirect(request.getContextPath() + SLASH + USERS);
    }
}
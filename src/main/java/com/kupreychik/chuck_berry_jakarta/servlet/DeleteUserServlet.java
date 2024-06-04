package com.kupreychik.chuck_berry_jakarta.servlet;

import com.kupreychik.chuck_berry_jakarta.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.DELETE;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.NAME;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.SLASH;
import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.USERS;

@Slf4j
@WebServlet(SLASH + USERS + SLASH + DELETE)
public class DeleteUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME);
        log.info("Deleting user with name: {}", name);
        userService.deleteUser(name);
        response.sendRedirect(request.getContextPath() + SLASH + USERS);
    }
}

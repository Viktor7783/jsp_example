package com.kupreychik.chuck_berry_jakarta.servlet;

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

import static com.kupreychik.chuck_berry_jakarta.consts.WebConsts.*;

/*@Slf4j - это аннотация из библиотеки Lombok, которая автоматически создает поле логгера в классе.
При компиляции Lombok генерирует такой код:
private static final Logger log = LoggerFactory.getLogger(MyServlet.class);*/
@Slf4j
@WebServlet(SLASH + USERS)
public class UserListServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //!!!
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UserListServlet doGet");
        request.setAttribute(USERS, userService.getUsers());
        request.getRequestDispatcher(USERS_LIST_JSP).forward(request, response);
    }
}

/*Вызов super.init(config) в методе init очень важен по нескольким причинам:
    Родительский класс HttpServlet выполняет критически важную инициализацию сервлета
    Сохраняет ServletConfig для дальнейшего использования
    Без этого вызова не будут работать методы getServletContext() и getServletConfig()
    Обеспечивает правильную инициализацию всего жизненного цикла сервлета
*/
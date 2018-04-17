package controller;

import static controller.MainController.*;
import bean.Counter;
import dao.UserDao;
import entity.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

@Controller
public class LoginController {

    public static final String LOGIN_PAGE = "/Pr4_bmw/login";
    private static final String LOGIN_URL = "/login";
    public static final String LOGIN = "login";
    private static final String PASSWORD = "pass";

    @Autowired
    private UserDao userDao;
    @Autowired
    private Counter counter;
    @Autowired
    private UserService userService;

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        User u = userService.getUserFromSession(request.getSession());
        mav.setViewName(LOGIN);
        mav.addObject(USER, u);
        mav.addObject(COUNTER, counter);
        return mav;
    }

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
    public void login(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = LOGIN, required = true) String login,
            @RequestParam(name = PASSWORD, required = true) String pass) throws IOException {

        if (login != null && pass != null) {
            User u = userDao.getByLogin(login);
            if (u != null && pass.equals(u.getPass())) {
                request.getSession().setAttribute(USER, u);
                counter.increment();
            }
        }
        response.sendRedirect(MAIN_PAGE);
       
    }
}

package controller;

import bean.Counter;
import static controller.MainController.*;
import static controller.LoginController.*;
import dao.UserDao;
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
public class RegistrationController {

    private static final String REGISTRATION_PAGE = "/Pr4_bmw/registration";
    private static final String REGISTRATION_URL = "/registration";
    private static final String REGISTRATION = "registration";
    static final String PASSWORD1 = "pass1";
    static final String PASSWORD2 = "pass2";

    @Autowired
    private Counter counter;
    @Autowired
    private UserService userService;

    @RequestMapping(value = REGISTRATION_URL, method = RequestMethod.GET)
    public ModelAndView registration(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(REGISTRATION);
        mav.addObject(USER, userService.getUserFromSession(request.getSession()));
        mav.addObject(COUNTER, counter);
        return mav;
    }

    @RequestMapping(value = REGISTRATION_URL, method = RequestMethod.POST)
    public void regisration(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = LOGIN, required = false) String login,
            @RequestParam(name = PASSWORD1, required = false) String pass1,
            @RequestParam(name = PASSWORD2, required = false) String pass2) throws IOException {
        if (userService.isRegistration(login, pass1, pass2)) {
            response.sendRedirect(LOGIN_PAGE);
        } else {
            response.sendRedirect(REGISTRATION_PAGE);
        }
    }
}

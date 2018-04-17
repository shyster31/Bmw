package controller;

import bean.Counter;
import static controller.MainController.*;
import static controller.RegistrationController.*;
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
public class ProfileController {
    
    private static final String PROFILE_PAGE = "/Pr4_bmw/profile";
    private static final String PROFILE_URL = "/profile";
    private static final String PROFILE = "profile";
    private static final String OLD_PASSWORD = "oldPass";
    
    @Autowired
    private Counter counter;
    @Autowired
    private UserService userService;

    @RequestMapping(value = PROFILE_URL, method = RequestMethod.GET)
    public ModelAndView profile(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
        User u = userService.getUserFromSession(request.getSession());
        if(u==null){
            response.sendRedirect(MAIN_PAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(PROFILE);
        mav.addObject(USER, u);
        mav.addObject(COUNTER, counter);
        return mav;
    }
    
    @RequestMapping(value = PROFILE_URL, method = RequestMethod.POST)
    public void changePassword(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = OLD_PASSWORD, required = false) String oldPass,
            @RequestParam(name = PASSWORD1, required = false) String pass1,
            @RequestParam(name = PASSWORD2, required = false) String pass2) throws IOException{
        if(userService.isChangeUserPassword(userService.getUserFromSession(request.getSession()),
                oldPass,
                pass1,
                pass2)){
            response.sendRedirect(MAIN_PAGE);
        }else{
            response.sendRedirect(PROFILE_PAGE);
        }
    }
}

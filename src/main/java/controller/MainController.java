package controller;

import bean.Counter;
import bean.ItemsCache;
import entity.Bmw;
import entity.User;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.AuthenticationService;
import service.ItemService;
import service.UserService;

@Controller
public class MainController {

    public static final String MAIN_PAGE = "/Pr4_bmw/main";
    private static final String MAIN_URL = "/main";
    private static final String EXIT = "exit";
    private static final String QUESTION = "q";
    private static final String CATEGORY = "cat";
    public static final String MAIN = "main";
    public static final String USER = "user";
    public static final String ITEMS = "items";
    public static final String COUNTER = "counter";

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private Counter counter;
    @Autowired
    private ItemsCache itemsCache;
    
    @RequestMapping(name = MAIN_URL, method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request,
            @RequestParam(name = EXIT, required = false) String exit,
            @RequestParam(name = QUESTION, required = false) String q,
            @RequestParam(name = CATEGORY, required = false) String cat) {

        authenticationService.removeUserAttributeFromSession(exit, request.getSession());
        Map<Integer, Bmw> its = itemsCache.getItems();
        User u = userService.getUserFromSession(request.getSession());
        List<Bmw> items = itemService.getItemsList(cat, q, its);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(MAIN);
        mav.addObject(USER, u);
        mav.addObject(ITEMS, items);
        mav.addObject(COUNTER, counter);
        return mav;
    }
}

package controller;

import bean.Counter;
import bean.ItemsCache;
import static controller.MainController.*;
import entity.Bmw;
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
import service.ItemService;
import service.UserService;

@Controller
public class ItemController {

    public static final String ID = "id";
    private static final String ITEM_URL = "/item";
    private static final String ITEM = "item";
    private static final String IS_LOGIN = "isLogin";

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemsCache itemsCache;
    @Autowired
    private Counter counter;

    @RequestMapping(value = ITEM_URL, method = RequestMethod.GET)
    public ModelAndView item(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = ID, required = true) String idString) throws IOException {
        User u = userService.getUserFromSession(request.getSession());
        ModelAndView mav = new ModelAndView();
        Integer id = itemService.getCorrectId(idString);
        if (id != null && id < itemsCache.length() && id > 0) {
            Bmw item = itemService.getItemById(id, itemsCache.getItems());
            mav.setViewName(ITEM);
            mav.addObject(ITEM, item);
            mav.addObject(COUNTER, counter);
            mav.addObject(USER, u);
            mav.addObject(IS_LOGIN, u != null);
        } else {
            response.sendRedirect(MAIN_PAGE);
        }

        return mav;
    }
}

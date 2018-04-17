package controller;

import bean.Counter;
import bean.ItemsCache;
import static controller.ItemController.ID;
import static controller.MainController.*;
import entity.Bmw;
import entity.User;
import java.io.IOException;
import java.util.List;
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
public class BasketController {

    
    private static final String BASKET_PAGE = "/Pr4_bmw/basket";
    private static final String BASKET_URL = "/basket";
    private static final String BASKET = "basket";

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemsCache itemsCache;
    @Autowired
    private Counter counter;

    @RequestMapping(value = BASKET_URL, method = RequestMethod.GET)
    public ModelAndView basket(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = ID, required = false) String idString) throws IOException {
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName(BASKET);
        User u = userService.getUserFromSession(request.getSession());
        Integer id = itemService.getCorrectId(idString);
        if(id != null){
            itemService.changeBasketItems(id, userService.getBasket(u), u);
            response.sendRedirect(BASKET_PAGE);
        }else{
            List<Bmw> items = itemService.getItemsById(userService.getBasket(u).items, itemsCache.getItems());
            mav.addObject(ITEMS, items);
            mav.addObject(COUNTER, counter);
            mav.addObject(USER, u);
        }
        return mav;
    }
}

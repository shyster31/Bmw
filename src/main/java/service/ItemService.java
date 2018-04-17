package service;

import com.google.gson.Gson;
import static controller.MainController.ITEMS;
import dao.ItemDaoCache;
import dao.UserDao;
import entity.Basket;
import entity.Bmw;
import entity.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private UserDao userDao;

    public List<Bmw> getItemsList(String cat, String q, Map<Integer, Bmw> its) {
        List<Bmw> items;
        if (cat != null) {
            items = ItemDaoCache.getByCat(cat, its);
        } else if (q != null) {
            items = new LinkedList<>();
            for (Bmw item : ItemDaoCache.get(its)) {
                if (item.getModel().contains(q)) {
                    items.add(item);
                }
            }
        } else {
            items = ItemDaoCache.get(its);
        }
        return items;
    }

    public Map<Integer, Bmw> getItemsFromContext(ServletContext context) {
        return (Map<Integer, Bmw>) context.getAttribute(ITEMS);
    }

    public Integer getCorrectId(String idKey) {
        Integer id = null;
        try {
            id = Integer.valueOf(idKey);
        } catch (Exception e) {

        }
        return id;
    }

    public Bmw getItemById(Integer id, Map<Integer, Bmw> items) {
        Set<Integer> its = items.keySet();
        for (Integer idKey : its) {
            if (id.equals(idKey)) {
                return items.get(idKey);
            }
        }
        return new Bmw();
    }
    
    public List<Bmw> getItemsById(List<Integer> ids, Map<Integer, Bmw> items){
        List<Bmw> itemsById = new LinkedList<>();
        for (Integer id : ids) {
            itemsById.add(getItemById(id, items));
        }
        return itemsById;
    }

    public void changeBasketItems(Integer id, Basket basket, User u) {
        if (id == 0) {
            basket.items.clear();
        }
        if (id > 0) {
            basket.items.add(id);
        }
        if (id < 0) {
            basket.items.remove(new Integer(-id));
        }
        u.setBasket(new Gson().toJson(basket));
    }
    
}

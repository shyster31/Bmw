package listener;

import bean.Counter;
import dao.ItemDao;
import entity.Bmw;
import entity.User;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import service.ItemService;
import service.UserService;

public class OurSessionListener implements HttpSessionListener{

    @Autowired
    private Counter counter;
    @Autowired
    private UserService userService;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
                
//        //cache
//        Map<Integer, Bmw> items = itemService.getItemsFromContext(se.getSession().getServletContext());
//        if(items == null){
//            items = new ConcurrentHashMap<>();
//            List<Bmw> from = itemDao.get();
//            for(Bmw item:from){
//                items.put(item.getId(), item);
//            }
//            se.getSession().getServletContext().setAttribute("items", items);
//        }
        
        //TODO start thread witch will update cache every 1 minute
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        User u = userService.getUserFromSession(se.getSession());
        if(u != null){
            counter.decrement();
        }
    }
    
}

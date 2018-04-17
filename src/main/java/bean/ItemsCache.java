package bean;

import dao.ItemDao;
import entity.Bmw;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ItemsCache {

//    @Autowired
    private ItemDao itemDao = new ItemDao(); 
    
    private Map<Integer, Bmw> items = initCache();

    public ItemsCache() {
        startToUpdateCache();
    }
  
    public Map<Integer, Bmw> getItems() {
        return items;
    }

    private Map<Integer, Bmw> initCache() {
        Map<Integer, Bmw> its = new HashMap<>();
        List<Bmw> from = itemDao.get();
        for (Bmw item : from) {
            its.put(item.getId(), item);
        }
        return its;
    }

    private void startToUpdateCache() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);
                    items = initCache();

                } catch (InterruptedException ex) {
                }
            }
        }).start();
    }
}

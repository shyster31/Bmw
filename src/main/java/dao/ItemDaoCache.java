package dao;

import entity.Bmw;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemDaoCache{
    public static List<Bmw> get(Map<Integer, Bmw> items){
        return getData(items);
    }
    
    public static List<Bmw> getByCat(String cat, Map<Integer, Bmw> items){
        List<Bmw> its = getData(items);
        List<Bmw> out = new LinkedList<>();
        for(Bmw item:its){
            if(item.getCat().equals(cat)){
                out.add(item);
            }
        }
        return out;
    }
    
    public static Bmw getById(int id, Map<Integer, Bmw> items){
        List<Bmw> its = getData(items);
        Bmw out = null;
        for(Bmw item:its){
            Integer id2 = item.getId();
            if(id2.equals(id)){
                out = item;
                return out;
            }
        }
        return new Bmw();
    }
    
    private static List<Bmw> getData(Map<Integer, Bmw> items){
        List<Bmw> out = new LinkedList<>();
        for(Bmw item:items.values()){
            out.add(item);
        }        
        return out;
    }
}

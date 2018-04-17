package service;

import com.google.gson.Gson;
import dao.UserDao;
import entity.Basket;
import entity.User;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
        
    public User getUserFromSession(HttpSession session){
        return (User)session.getAttribute("user");
    }
    public boolean isChangeUserPassword(User u, String oldPass, String pass1, String pass2){
        if(u != null && u.getPass().equals(oldPass) && pass1 != null && pass1.equals(pass2)){
            u.setPass(pass2);
            userDao.update(u);
            return true;
        }
        return false;
    }
    
    public boolean isRegistration(String login, String pass1, String pass2){
        if(!login.equals("") && pass1 != null && pass2 != null && pass1.equals(pass2)){
            User u = userDao.getByLogin(login);
            if(u == null){
                userDao.add(createNewUser(login, pass1));
                return true;
            }
        }
        return false;
    }
    
    private User createNewUser(String login, String pass){
        return new User(new Random().nextInt(), login, pass, new Gson().toJson(new Basket()));
    }
    
    public Basket getBasket(User u){
        Gson gson = new Gson();
        return gson.fromJson(u.getBasket(), Basket.class);
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
        userDao.update(u);
    }
}

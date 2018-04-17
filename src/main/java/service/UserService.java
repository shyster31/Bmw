package service;

import com.google.gson.Gson;
import dao.UserDao;
import entity.Basket;
import entity.User;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringContextHolder;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    
    public User getUserFromSession(HttpSession session){
        return (User)session.getAttribute("user");
    }
    public void changeUserPassword(User u, String oldPass, String pass1, String pass2){
        if(u != null && u.getPass().equals(oldPass) 
                && pass1 != null && pass1.equals(pass2)){
            u.setPass(pass2);
            userDao.update(u);
        }
    }
    
    public Basket getBasket(User u){
        Gson gson = new Gson();
        return gson.fromJson(u.getBasket(), Basket.class);
    }
}

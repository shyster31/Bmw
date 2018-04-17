package service;

import bean.Counter;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    @Autowired
    private Counter counter;
    
    public void removeUserAttributeFromSession(String exit, HttpSession session){
        if(exit != null && session.getAttribute("user") != null){
            session.removeAttribute("user");
            counter.decrement();
        }
    }
}

package listener;

import bean.Counter;
import entity.User;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

public class OurSessionListener implements HttpSessionListener {

    @Autowired
    private Counter counter;
    @Autowired
    private UserService userService;

    @Override
    public void sessionCreated(HttpSessionEvent se) {}

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        User u = userService.getUserFromSession(se.getSession());
        if (u != null) {
            counter.decrement();
        }
    }

}

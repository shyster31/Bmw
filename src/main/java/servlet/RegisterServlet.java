package servlet;

import com.google.gson.Gson;
import dao.UserDao;
import entity.Basket;
import entity.User;
import html.HtmlFormer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.SpringContextHolder;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        response.setContentType("text/html;charset=UTF-8");
        HtmlFormer html = (HtmlFormer)SpringContextHolder.getContext().getBean("html");
        try (PrintWriter out = response.getWriter()) {
            out.println(
                    html.registerContent(
                            html.header("Register Page", u), 
                            html.footer(((AtomicInteger)request.getServletContext().getAttribute("counter")).get())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        if(login != null && pass1 != null && pass2 != null && pass1.equals(pass2)){
            User u = ((UserDao)SpringContextHolder.getContext().getBean("user_dao")).getByLogin(login);
            if(u == null){
                u = new User(new Random().nextInt(), login, pass1, new Gson().toJson(new Basket()));
                ((UserDao)SpringContextHolder.getContext().getBean("user_dao")).add(u);
            }
        }
        response.sendRedirect("/Pr4_bmw/login");
    }

}

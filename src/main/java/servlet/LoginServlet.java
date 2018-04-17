package servlet;

import dao.UserDao;
import entity.User;
import html.HtmlFormer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.SpringContextHolder;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        response.setContentType("text/html;charset=UTF-8");
        HtmlFormer html = (HtmlFormer)SpringContextHolder.getContext().getBean("html");
        try (PrintWriter out = response.getWriter()) {
            out.println(
                    html.loginContent(
                            html.header("Sign In Page", u), 
                            html.footer(((AtomicInteger)request.getServletContext().getAttribute("counter")).get())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        if(login != null && pass != null){
            User u = ((UserDao)SpringContextHolder.getContext().getBean("user_dao")).getByLogin(login);
            if(u != null && pass.equals(u.getPass())){
                request.getSession().setAttribute("user", u);
                ((AtomicInteger)request.getServletContext().getAttribute("counter")).incrementAndGet();
            }
        }
        response.sendRedirect("/Pr4_bmw/main");
    }

}

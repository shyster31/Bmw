package servlet;

import dao.UserDao;
import entity.User;
import html.HtmlFormer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserService;
import spring.SpringContextHolder;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = new UserService().getUserFromSession(request.getSession());
        if (u == null) {
            response.sendRedirect("/Pr4_bmw/main");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            HtmlFormer html = (HtmlFormer)SpringContextHolder.getContext().getBean("html");
            try (PrintWriter out = response.getWriter()) {
                out.println(html.profileContent(
                        html.header("Change password", u),
                        html.footer(((AtomicInteger) request.getServletContext().getAttribute("counter")).get()),
                        u.getLogin()));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new UserService().changeUserPassword(
                new UserService().getUserFromSession(request.getSession()), 
                request.getParameter("oldPass"), 
                request.getParameter("pass1"), 
                request.getParameter("pass2"));
        response.sendRedirect("/Pr4_bmw/main");
    }

}

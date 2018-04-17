package servlet;

import dao.ItemDao;
import dao.ItemDaoCache;
import entity.Bmw;
import entity.User;
import html.HtmlFormer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AuthenticationService;
import service.ItemService;
import service.UserService;
import spring.SpringContextHolder;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exit = request.getParameter("exit");
        String q = request.getParameter("q");
        String cat = request.getParameter("cat");
        new AuthenticationService().removeUserAttributeFromSession(exit, request.getSession());
        Map<Integer, Bmw> its = new ItemService().getItemsFromContext(request.getServletContext());
        User u = new UserService().getUserFromSession(request.getSession());
        List<Bmw> items = new ItemService().getItemsList(cat, q, its);
        response.setContentType("text/html;charset=UTF-8");
        HtmlFormer html = (HtmlFormer)SpringContextHolder.getContext().getBean("html");
        try (PrintWriter out = response.getWriter()) {
            out.println(html.mainContent(
                    html.header("BMW Motors Ukraine", u), 
                    html.footer(((AtomicInteger)request.getServletContext().getAttribute("counter")).get()), items));
        }
    }

}

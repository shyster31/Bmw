package servlet;

import dao.ItemDaoCache;
import entity.Bmw;
import entity.User;
import html.HtmlFormer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.SpringContextHolder;

public class ItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        String idString = request.getParameter("id");
        if (idString == null) {
            response.sendRedirect("/Pr4_bmw/main");
        } else {
            int id = 0;
            Map<Integer, Bmw> its = (Map<Integer, Bmw>)request.getServletContext().getAttribute("items");
            try {
                id = Integer.parseInt(idString);
                Bmw item = ItemDaoCache.getById(id, its);
                response.setContentType("text/html;charset=UTF-8");
                HtmlFormer html = (HtmlFormer)SpringContextHolder.getContext().getBean("html");
                try (PrintWriter out = response.getWriter()) {
                    out.println(html.itemContent(
                            html.header(item.getModel(), u), 
                            html.footer(((AtomicInteger)request.getServletContext().getAttribute("counter")).get()), 
                            item, 
                            u != null));
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/Pr4_bmw/main");
            }
        }
    }
}

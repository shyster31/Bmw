package servlet;

import com.google.gson.Gson;
import dao.ItemDaoCache;
import dao.UserDao;
import entity.Basket;
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
import spring.SpringContextHolder;

public class BasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        if (u == null) {
            response.sendRedirect("/Pr4_bmw/main");
        } else {
            String id = request.getParameter("id");
            Gson gson = new Gson();
            Basket basket = gson.fromJson(u.getBasket(), Basket.class);
            if (id != null) {
                try{
                    int idInt = Integer.parseInt(id);
                    if(idInt == 0){
                        basket.items.clear();                        
                    }
                    if(idInt > 0){
                        basket.items.add(idInt);
                    }
                    if(idInt < 0){
                        basket.items.remove(new Integer(-idInt));
                    }
                    u.setBasket(gson.toJson(basket));
                    ((UserDao)SpringContextHolder.getContext().getBean("user_dao")).update(u);
                    response.sendRedirect("/Pr4_bmw/basket");
                }catch(Exception e){e.printStackTrace();}
            } else {
                response.setContentType("text/html;charset=UTF-8");
                List<Bmw> items = new LinkedList<>();
                Map<Integer, Bmw> its = (Map<Integer, Bmw>)request.getServletContext().getAttribute("items");
                for(Integer i:basket.items){
                    items.add(ItemDaoCache.getById(i, its));
                }
                HtmlFormer html = (HtmlFormer)SpringContextHolder.getContext().getBean("html");
                try (PrintWriter out = response.getWriter()) {
                    out.println(html.basketContent(html.header("Basket", u), html.footer(((AtomicInteger)request.getServletContext().getAttribute("counter")).get()), items));
                }
            }
        }
    }

}
